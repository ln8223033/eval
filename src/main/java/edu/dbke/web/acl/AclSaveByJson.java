package edu.dbke.web.acl;

import edu.dbke.web.acl.Module.Menu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhonghua Hu
 *
 */
public class AclSaveByJson {

	protected static String GET_ACL = "0";
	protected static String GET_ACL_MENU = "1";

	/**
	 * 解析json数据并保存到arrayList中
	 * @param jsonArray
	 * @param loginType
	 * @return
	 */
	protected static List<Module> saveAclJsonToSession(JSONArray jsonArray, String loginType) {
		List<Module> acl = new ArrayList<Module>();
		if (jsonArray == null || jsonArray.toString().trim().length() <= 0 || "null".equals(jsonArray)) {
			return null;
		}
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			if (jsonObject != null) {
				Module module = new Module();
				Menu menu = null;
				if (GET_ACL_MENU.equals(loginType)) {
					menu = new Menu();
					JSONObject menuObject = jsonObject.isNull("menu") ? null : (JSONObject) jsonObject.get("menu");
					if (menuObject != null) {
						menu.setIcon(menuObject.optString("icon", null));
						menu.setId(menuObject.optString("id", null));
						menu.setName(menuObject.optString("name", null));
						menu.setOrderNum(menuObject.optInt("orderNum", -1));
						menu.setParent_id(menuObject.optString("parent_id", null));
					}
				}
				module.setMenu(menu);
				module.setAcl(jsonObject.optString("acl", null));
				module.setAclUrl(jsonObject.optString("aclUrl", null));
				module.setSn(jsonObject.optString("sn", null));
				module.setUrl(jsonObject.optString("url", null));
				acl.add(module);
			}
		}
		return acl;
	}

	protected static List<String> saveModuleJsonToSession(JSONArray jsonArray) {
		List<String> urls = new ArrayList<String>();
		if (jsonArray == null || jsonArray.toString().trim().length() <= 0 || "null".equals(jsonArray)) {
			return null;
		}
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			if (jsonObject.optString("url", null) != null) {
				urls.add(jsonObject.optString("url", null));
			}
			if (jsonObject.optString("aclUrl", null) != null) {
				urls.add(jsonObject.optString("aclUrl", null));
			}
		}
		return urls;
	}

	public static void main(String[] args) throws IOException, JSONException {
		//		ObjectMapper jo = new ObjectMapper();
		String s = "{\"Msg\":[{\"sn\":null,\"acl\":\"0,0,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"sys/syss\",\"url\":null},"
				+ "{\"sn\":null,\"acl\":\"0,0,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"sys/syss\",\"url\":null},"
				+ "{\"sn\":null,\"acl\":\"0,0,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"sysss/sys\",\"url\":null},"
				+ "{\"sn\":null,\"acl\":\"0,0,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"syss/sysss\",\"url\":null},"
				+ "{\"sn\":null,\"acl\":\"0,0,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"syss/syssss\",\"url\":null},"
				+ "{\"sn\":null,\"acl\":\"0,0,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"sysssss/sys\",\"url\":null},"
				+ "{\"sn\":null,\"acl\":\"0,0,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"syss/syssss\",\"url\":null},"
				+ "{\"sn\":null,\"acl\":\"0,1,0,0\",\"menu\":{\"icon\":null,"
				+ "\"name\":\"系统管理\",\"orderNum\":\"1\",\"parent_id\":null},\"aclUrl\":\"sys/sys\",\"url\":null},],\"ProResult\":\"Success\"}";
		JSONObject object = new JSONObject(s);
		System.out.println(object.get("Msg").toString());

		//		Module[] modules = jo.readValue(object.get("Msg").toString(), Module[].class);
		List<Module> mList = new ArrayList<Module>();
		//		for (Module m : modules) {
		//			mList.add(m);
		//			List<Module> l = mList;
		//			mList.addAll(l);
		//		}
		long before1 = System.currentTimeMillis();
		boolean d = AclValidateUrl.judgeUrl("sys/sys!add.action", mList);
		long after1 = System.currentTimeMillis();
		System.out.println((after1 - before1) + ":" + d);

		//		long before0 = System.currentTimeMillis();
		//		boolean c = OptURI.isRetrieve("sys/sys/list", mList);
		//		long after0 = System.currentTimeMillis();
		//		System.out.println((after0 - before0) + ":" + c);

		/*"add".toUpperCase().equals(Option.ADD.getIndex());

		EnumSet<Option> enumSet = EnumSet.allOf(Option.class);
		for (Option o : enumSet) {
			if (o.getIndex() == 2) {
				System.out.println(o.getName());
			}
		}*/
		//		System.out.println(analyzeUri("/acl/acl!add.action?id=1&time=")[0]);
		//		System.out.println(analyzeUri("/acl/acl!add.action?id=1&time=")[1]);
	}
}