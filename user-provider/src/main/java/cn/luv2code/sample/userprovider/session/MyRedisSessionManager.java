package cn.luv2code.sample.userprovider.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.io.Serializable;

@Component
public class MyRedisSessionManager extends DefaultSessionManager {
    public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    RedisSessionDao redisSessionDao;

    @Override
    public void setSessionDAO(SessionDAO sessionDAO) {
        setSessionDAO(redisSessionDao);
    }

    /**
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     * @apiNote 减少从redis读取session次数
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        logger.debug("retrieveSession");
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (request != null && sessionId != null) {
            Session session = (Session) request.getAttribute(sessionId.toString());
            if (session != null) {
                return session;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (request != null && sessionId != null) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
