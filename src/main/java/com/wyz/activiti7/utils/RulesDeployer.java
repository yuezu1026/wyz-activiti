package com.wyz.activiti7.utils;


import java.util.Map;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.deploy.Deployer;
import org.activiti.engine.impl.persistence.deploy.DeploymentManager;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.ResourceEntity;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tom Baeyens
 */
public class RulesDeployer implements Deployer {

  private static final Logger log = LoggerFactory.getLogger(RulesDeployer.class);

  public void deploy(DeploymentEntity deployment, Map<String, Object> deploymentSettings) {
    log.debug("Processing deployment {}", deployment.getName());

    KnowledgeBuilder knowledgeBuilder = null;

    DeploymentManager deploymentManager = Context.getProcessEngineConfiguration().getDeploymentManager();

    Map<String, ResourceEntity> resources = deployment.getResources();
    for (String resourceName : resources.keySet()) {
      log.info("Processing resource {}", resourceName);
      if (resourceName.endsWith(".drl")) { // is only parsing .drls sufficient? what about other rule dsl's? (@see ResourceType)
        if (knowledgeBuilder == null) {
          knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        }
        ResourceEntity resourceEntity = resources.get(resourceName);
        byte[] resourceBytes = resourceEntity.getBytes();
        Resource droolsResource = ResourceFactory.newByteArrayResource(resourceBytes);
        knowledgeBuilder.add(droolsResource, ResourceType.DRL);
      }
    }

    if (knowledgeBuilder != null) {
      KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
      deploymentManager.getKnowledgeBaseCache().add(deployment.getId(), knowledgeBase);
    }
  }
}
