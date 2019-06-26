package rabbitmq;

public class MessagingProperties
{
    private String host;
    private String password;
    private String adminHttpPort;
    private String userName;

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getAdminHttpPort()
    {
        return adminHttpPort;
    }

    public void setAdminHttpPort(String adminHttpPort)
    {
        this.adminHttpPort = adminHttpPort;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
