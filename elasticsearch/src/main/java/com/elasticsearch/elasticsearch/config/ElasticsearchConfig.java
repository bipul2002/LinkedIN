    package com.elasticsearch.elasticsearch.config;

    import com.elasticsearch.elasticsearch.entity.User;
    import org.apache.http.conn.ssl.TrustAllStrategy;
    import org.apache.http.ssl.SSLContextBuilder;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.elasticsearch.client.ClientConfiguration;
    import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
    import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
    import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

    import javax.net.ssl.SSLContext;

    @Configuration
    @EnableElasticsearchRepositories(basePackages = " com.elasticsearch.elasticsearch.elastisearchRepository")
    public class ElasticsearchConfig extends ElasticsearchConfiguration {
        @Override
        public ClientConfiguration clientConfiguration() {
            return ClientConfiguration.builder().connectedTo("localhost:9200")
                   // .usingSsl(buildSSLContext())
                    .withBasicAuth("elastic","elastic")
                    .build();
        }

//        private static SSLContext buildSSLContext() {
//            try{
//                return new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();
//            } catch (Exception e){
//                throw new RuntimeException(e);
//            }
//        }



    }
