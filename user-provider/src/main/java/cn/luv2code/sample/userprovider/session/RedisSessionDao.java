package cn.luv2code.sample.userprovider.session;

import cn.luv2code.sample.userprovider.utils.ByteUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @apiNote redis管理session的Dao
 */
@Component
public class RedisSessionDao extends AbstractSessionDAO {
    public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource
    private RedisTemplate<Byte[], Byte[]> redisTemplate;
    private static final String SHIRO_SESSION_PREFIX = "shiro_session";

    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    @Override
    protected Serializable doCreate(Session session) {
        logger.debug("doCreate(Session session)");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("doReadSession(Serializable sessionId)");
        if (sessionId == null) {
            return null;
        }
        byte[] key = getKey(sessionId.toString());
        Byte[] value = redisTemplate.opsForValue().get(ByteUtil.toObjects(key));
        Session session = (Session) SerializationUtils.deserialize(ByteUtil.toPrimitives(value));
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
//        assignSessionId(session,session.getId());
        logger.debug("update(Session session)");
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        logger.debug("delete(Session session)");
        if (session == null || session.getId() == null) {
            return;
        } else {
            byte[] key = getKey(session.getId().toString());
            redisTemplate.delete(ByteUtil.toObjects(key));
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        logger.debug("getActiveSessions");
        Set<Byte[]> set = redisTemplate.keys(ByteUtil.toObjects((SHIRO_SESSION_PREFIX+"*").getBytes()));
        Set<Session> sessions = new HashSet<>();
        if (CollectionUtils.isEmpty(set)) {
            return sessions;
        }
        for (Byte[] bytes: set) {
            Session session = (Session) SerializationUtils.deserialize(ByteUtil.toPrimitives(redisTemplate.opsForValue().get(bytes)));
            sessions.add(session);
        }
        return sessions;
    }

    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            redisTemplate.opsForValue().set(ByteUtil.toObjects(key), ByteUtil.toObjects(value));
        } else {
            return;
        }
    }
}
