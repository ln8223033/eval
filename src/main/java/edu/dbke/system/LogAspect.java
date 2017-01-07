package edu.dbke.system;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class LogAspect {
	public LogAspect() {
		System.out.println("log aspect ------------------------");
	}

	//~ Static fields ==================================================================================================
	//缓存有@OperationDescription方法参数名称
	private static Map<String, String[]> parameterNameCaches = new ConcurrentHashMap<String, String[]>();
	//缓存SPEL Expression
	private static Map<String, Expression> spelExpressionCaches = new ConcurrentHashMap<String, Expression>();

	private static ExpressionParser parser = new SpelExpressionParser();
	private static LocalVariableTableParameterNameDiscoverer parameterNameDiscovere = new LocalVariableTableParameterNameDiscoverer();

	//~ Instance fields ================================================================================================
	private Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Autowired
	private OperLogService operLogService;

	//~ Methods ========================================================================================================
	/**
	 * 对所有actoin类，方法含有@OperationDescription，进行@Around拦截
	 */
	@Around("execution(* edu.dbke.web.action.*.*.*(..)) && @annotation(annotation)")
	public Object advice(ProceedingJoinPoint joinPoint, OperationDescription annotation) throws Throwable {
		Object result = joinPoint.proceed();

		saveLog(joinPoint, annotation);
		return result;
	}

	/**
	 * 保存操作日志
	 * 
	 * @param joinPoint
	 * @param annotation
	 */
	private void saveLog(ProceedingJoinPoint joinPoint, OperationDescription annotation) {
		String descpTemp = annotation.description();
		String descption = executeTemplate(descpTemp, joinPoint);
		String entityType = annotation.entityType();
		OperationType type = annotation.type();

		if (logger.isDebugEnabled()) {
			String methodName = joinPoint.getSignature().getName();
			logger.debug("Action Type: {}, Entity Type: {}, Action method: {}, Description: {}", new Object[] {
					type.getType(), entityType, methodName, descption });
		}
		//取到当前的操作用户   
		//Seat user = SecurityContextUtil.getCurrentUser();
		/*if (user != null) {
			try {
				OperLog operLog = new OperLog();

				operLog.setCreatetime(new Date());
				operLog.setOperatorId(user.getId());
				operLog.setOperatorName(user.getSeatname());
				operLog.setExeOperation(descption);
				operLog.setExeType(type.getType());
				operLog.setEntityType(entityType);

				operLogService.insertEntity(operLog);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}*/
	}

	/**
	 * 解析执行OperationDescription 的description模板。
	 * 
	 * @param template
	 * @param joinPoint
	 * @return
	 */
	private String executeTemplate(String template, ProceedingJoinPoint joinPoint) {
		// get method parameter name
		String methodLongName = joinPoint.getSignature().toLongString();
		String[] parameterNames = parameterNameCaches.get(methodLongName);
		if (parameterNames == null) {
			Method method = getMethod(joinPoint);
			parameterNames = parameterNameDiscovere.getParameterNames(method);
			parameterNameCaches.put(methodLongName, parameterNames);
		}

		// add args to expression context
		StandardEvaluationContext context = new StandardEvaluationContext();
		Object[] args = joinPoint.getArgs();
		if (args.length == parameterNames.length) {
			for (int i = 0, len = args.length; i < len; i++)
				context.setVariable(parameterNames[i], args[i]);
		}

		//cacha expression
		Expression expression = spelExpressionCaches.get(template);
		if (expression == null) {
			expression = parser.parseExpression(template, new TemplateParserContext());
			spelExpressionCaches.put(template, expression);
		}
		String value = expression.getValue(context, String.class);
		return value;
	}

	/**
	 * 获取当前执行的方法
	 * @param joinPoint
	 * @return
	 */
	private Method getMethod(ProceedingJoinPoint joinPoint) {
		String methodLongName = joinPoint.getSignature().toLongString();
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		Method method = null;
		for (int i = 0, len = methods.length; i < len; i++) {
			if (methodLongName.equals(methods[i].toString())) {
				method = methods[i];
				break;
			}
		}
		return method;
	}
}