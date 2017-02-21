package hu.schonherz.training.helpdesk.web;

public final class BeanConstants {
    public static final String JNDI_GLOBAL_PREFIX = "java:global/";
    public static final String JNDI_ADMIN_MODULE = "admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/";

    public static final String JNDI_LOGIN_STATISTICS_SERVICE = JNDI_GLOBAL_PREFIX
        + JNDI_ADMIN_MODULE
        + "RpcLoginStatisticsBean";

    public static final String JNDI_LOGIN_SERVICE = JNDI_GLOBAL_PREFIX
        + JNDI_ADMIN_MODULE
        + "RpcLoginServiceBean!hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote";

    private BeanConstants() {}
}
