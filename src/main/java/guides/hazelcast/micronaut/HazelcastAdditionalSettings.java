package guides.hazelcast.micronaut;

import com.hazelcast.config.JoinConfig;
import io.micronaut.cache.hazelcast.HazelcastMemberConfiguration;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;

import javax.inject.Singleton;

@Singleton
public class HazelcastAdditionalSettings
        implements BeanCreatedEventListener<HazelcastMemberConfiguration> {

    public HazelcastMemberConfiguration onCreated(BeanCreatedEvent<HazelcastMemberConfiguration> event) {
        HazelcastMemberConfiguration configuration = event.getBean();
        configuration.getGroupConfig().setName("micronaut-cluster");
        JoinConfig joinConfig = configuration.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getAwsConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).addMember("127.0.0.1");
        return configuration;
    }
}