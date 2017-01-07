package edu.dbke.web.acl;

/**
 * @author Zhonghua Hu
 */
public class Module {
    private String sn;//模块编码
    private String url;//访问地址
    private String aclUrl;//操作基址
    private String acl;//授权详情
    private Menu menu;//菜单

    /**
     * 菜单内部静态类
     *
     * @author Zhonghua Hu
     */
    public static class Menu {
        private String parent_id;
        private String id;
        private String name;
        private String icon;
        private Integer orderNum;

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Integer getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(Integer orderNum) {
            this.orderNum = orderNum;
        }
    }

        public Menu getMenu() {
        return menu;
    }

        public void setMenu(Menu menu) {
        this.menu = menu;
    }

        public String getSn() {
        return sn;
    }

        public void setSn(String sn) {
        this.sn = sn;
    }

        public String getUrl() {
        return url;
    }

        public void setUrl(String url) {
        this.url = url;
    }

        public String getAclUrl() {
        return aclUrl;
    }

        public void setAclUrl(String aclUrl) {
        this.aclUrl = aclUrl;
    }

        public String getAcl() {
        return acl;
    }

        public void setAcl(String acl) {
        this.acl = acl;
    }

}
