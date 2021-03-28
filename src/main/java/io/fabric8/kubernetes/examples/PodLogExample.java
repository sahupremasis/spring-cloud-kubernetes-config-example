package io.fabric8.kubernetes.examples;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PodLogExample {

    private static final Logger logger = LoggerFactory.getLogger(PodLogExample.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: podName [namespace]");
            return;
        }

        String podName = args[0];
        String namespace = "default";


        if (args.length > 1) {
            namespace = args[1];
        }

        System.out.println("Log of pod " + podName + " in " + namespace + " is:");
        System.out.println("----------------------------------------------------------------");

        try (
                KubernetesClient client = new DefaultKubernetesClient();
                LogWatch watch = client.pods().inNamespace(namespace).withName(podName).tailingLines(10).watchLog(System.out)
        ) {
            PodList podList = client.pods().inNamespace("default").list();
            System.out.println("podList is: " + podList);
            List<Pod> pods = podList.getItems();
            pods.forEach(pod -> System.out.println(pod.getMetadata().getName() + "   --" +  pod.getStatus().getPhase() + "  "  + pod.getStatus().getStartTime()));
            Object deleteMessage =  client.pods().inNamespace("default").withName("config-spring-cloud-k8s-example-b7c88746-tns2q").delete();
            System.out.println("deleteMessage " + deleteMessage);
            Thread.sleep(5 * 1000L);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}