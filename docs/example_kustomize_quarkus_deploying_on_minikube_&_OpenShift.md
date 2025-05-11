<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" class="logo" width="120"/>

# example using kustomize with quarkus deploying on minikube

To deploy a Quarkus application on Minikube using Kustomize, follow this integrated approach combining Quarkus' Kubernetes tooling with Kustomize's configuration management:

### 1. **Project Setup**

Add required Quarkus extensions:

```bash
./mvnw quarkus:add-extension -Dextensions='quarkus-kubernetes,quarkus-minikube,quarkus-container-image-jib'
```

This enables Kubernetes manifest generation ([^1_1][^1_3]), Minikube optimizations ([^1_1]), and container image building ([^1_1]).

### 2. **Configure Application Properties**

`src/main/resources/application.properties`:

```properties
quarkus.kubernetes.namespace=quarkus-app
quarkus.container-image.build=true
quarkus.minikube.enabled=true
quarkus.kubernetes.service-type=NodePort
```


### 3. **Kustomize Structure**

Create this directory layout:

```
k8s/
├── base/
│   ├── kustomization.yaml
│   └── patch.yaml
└── overlays/
    └── minikube/
        ├── kustomization.yaml
        └── replicas-patch.yaml
```

**base/kustomization.yaml**:

```yaml
resources:
- target/kubernetes/minikube.yaml
```

**base/patch.yaml** (optional annotations):

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: quarkus-app
spec:
  template:
    metadata:
      annotations:
        prometheus.io/scrape: "true"
```

**overlays/minikube/kustomization.yaml**:

```yaml
namespace: quarkus-app
bases:
- ../../base
patchesStrategicMerge:
- replicas-patch.yaml
```

**overlays/minikube/replicas-patch.yaml**:

```yaml
apiVersion: apps/v1
kind: Deployment
spec:
  replicas: 2
```


### 4. **Build and Deploy**

```bash
# Build image directly in Minikube's Docker daemon
eval $(minikube docker-env)
./mvnw clean package

# Apply Kustomize configuration
kubectl apply -k k8s/overlays/minikube
```


### 5. **Verify Deployment**

```bash
minikube service list -n quarkus-app
kubectl get pods -n quarkus-app
```


### Key Integration Points:

1. **Minikube-Specific Manifests**
Quarkus generates `minikube.yaml` with optimized settings for local development ([^1_1][^1_3]), including:

- NodePort service type
- Docker image built directly in Minikube's environment
- Auto-configured health checks when using SmallRye Health ([^1_1])

2. **Kustomize Overrides**
The overlay allows environment-specific adjustments like:
```yaml
# Scale for development
spec:
  replicas: 1
  resources:
    limits:
      memory: 512Mi
```

while maintaining base configuration inheritance ([^1_4][^1_5]).

3. **Image Management**
Quarkus handles image building through Jib ([^1_1]), while Kustomize manages deployment variations without modifying build artifacts ([^1_4]).

### Comparison: Kustomize vs Quarkus Defaults

| Feature | Quarkus Native | Kustomize |
| :-- | :-- | :-- |
| Environment-specific config | application.properties | Overlay patches |
| Resource patching | Limited | Full YAML control |
| Image management | Integrated | External |
| Deployment workflow | Single command | Multi-step |

This approach combines Quarkus' developer experience with Kustomize's flexibility, ideal for teams transitioning from local development (Minikube) to production clusters ([^1_1][^1_4]).

<div style="text-align: center">⁂</div>

[^1_1]: https://quarkus.io/guides/deploying-to-kubernetes

[^1_2]: https://piotrminkowski.com/2020/08/10/guide-to-quarkus-on-kubernetes/

[^1_3]: https://kubebyexample.com/learning-paths/developing-quarkus-kubernetes/getting-started-quarkus/running-kubernetes

[^1_4]: https://dev.to/bansikah/deploying-a-nodejs-application-with-kustomize-on-minikube-443k

[^1_5]: https://redhat-scholars.github.io/argocd-tutorial/argocd-tutorial/03-kustomize.html

[^1_6]: https://stackoverflow.com/questions/75486207/kustomize-on-ingress-on-kubernetes-minikube-cluster

[^1_7]: https://dev.to/marcuspaulo/tutorial-publish-a-quarkus-application-in-kubernetes-minikube-and-dockerhub-36nd

[^1_8]: https://sonataflow.org/serverlessworkflow/latest/use-cases/advanced-developer-use-cases/data-index/data-index-usecase-singleton.html

[^1_9]: https://stackoverflow.com/questions/76401336/quarkus-deploy-in-new-quarkus-3-how-should-it-work-with-kubernetes

[^1_10]: https://www.opensourcerers.org/2023/05/08/how-to-set-up-and-demonstrate-quarkus-grumpycat-gitops-demo/

[^1_11]: https://github.com/manusa/yakc/blob/master/quickstarts/quarkus-dashboard/README.md

[^1_12]: https://opensource.com/article/22/5/quarkus-kubernetes-secrets

[^1_13]: https://livebook.manning.com/book/kubernetes-native-microservices/chapter-2/v-1/

[^1_14]: https://www.wallarm.com/cloud-native-products-101/helm-vs-kustomize-kubernetes-configuration

[^1_15]: https://github.com/quarkusio/quarkus/discussions/22062

[^1_16]: https://kubebyexample.com/learning-paths/argo-cd/argo-cd-working-kustomize

[^1_17]: https://www.opensourcerers.org/2023/06/05/java-to-pod/


---

# Can we write kustomize overlay patches to distinguish between the minikube k8s deployment and the Openshift deployment in chapter 11 of the book Quarkus in Action.

To distinguish between Minikube and OpenShift deployments using Kustomize overlays in a Quarkus project, here's a structured approach based on Quarkus' Kubernetes tooling and Kustomize's patching capabilities:

### Implementation Strategy

**1. Base Structure**
Create common manifests in `k8s/base` using Quarkus-generated resources:

```bash
k8s/
├── base/
│   ├── kustomization.yaml
│   └── deployment-patch.yaml
├── overlays/
│   ├── minikube/
│   └── openshift/
```

**base/kustomization.yaml**:

```yaml
resources:
- target/kubernetes/kubernetes.yml  # Base Kubernetes manifests
```

**2. Minikube Overlay**
`overlays/minikube/kustomization.yaml`:

```yaml
bases:
- ../../base
patchesStrategicMerge:
- service-patch.yaml
- minikube-specifics.yaml
```

**Minikube Service Patch** (NodePort configuration):

```yaml
apiVersion: v1
kind: Service
metadata:
  name: quarkus-app
spec:
  type: NodePort
  ports:
  - port: 8080
    nodePort: 30080
```

*(Quarkus Minikube extension auto-configures Docker environment[^2_2])*

**3. OpenShift Overlay**
`overlays/openshift/kustomization.yaml`:

```yaml
bases:
- ../../base
patchesStrategicMerge:
- route-patch.yaml
- openshift-specifics.yaml
```

**OpenShift Route Patch**:

```yaml
apiVersion: route.openshift.io/v1
kind: Route
metadata:
  name: quarkus-app
spec:
  port:
    targetPort: 8080
  to:
    kind: Service
    name: quarkus-app
```

*(Requires OpenShift extension[^2_5])*

### Key Differentiators

| Aspect | Minikube Overlay | OpenShift Overlay |
| :-- | :-- | :-- |
| Service Type | NodePort[^2_2] | ClusterIP |
| Network Exposure | `minikube service` command | OpenShift Route[^2_5] |
| Image Building | Local Docker daemon (`eval $(minikube...)`) | Source-to-Image (s2i) builds[^2_5] |
| Health Checks | Liveness/Readiness probes | Integrated with OpenShift health checks |
| Deployment Strategy | Rolling update | Blue-green deployments |

### Build Commands

```bash
# Minikube deployment
eval $(minikube docker-env)
kubectl apply -k k8s/overlays/minikube

# OpenShift deployment
oc apply -k k8s/overlays/openshift
```


### Quarkus Configuration

`application.properties`:

```properties
# Common
quarkus.container-image.group=my-registry
quarkus.kubernetes.deployment-target=kubernetes,openshift

# Minikube-specific
%minikube.quarkus.kubernetes.service-type=NodePort

# OpenShift-specific
%openshift.quarkus.openshift.expose=true
```

This approach maintains environment-specific configurations while preserving a single source of truth for base manifests, aligning with Quarkus' multi-environment deployment capabilities[^2_2][^2_5].

<div style="text-align: center">⁂</div>

[^2_1]: https://quarkus.io/guides/deploying-to-openshift-howto

[^2_2]: https://quarkus.io/guides/deploying-to-kubernetes

[^2_3]: https://docs.redhat.com/en/documentation/red_hat_build_of_quarkus/2.7/html-single/deploying_your_quarkus_applications_to_openshift_container_platform/index

[^2_4]: https://sciendo.com/2/v2/download/chapter/9789355515797/10.0000/9789355515797-001.pdf?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VycyI6W3sic3ViIjoyNTY3ODUxNywicHVicmVmIjoiNzY0NDg4IiwibmFtZSI6Ikdvb2dsZSBHb29nbGVib3QgLSBXZWIgQ3Jhd2xlciBTRU8iLCJ0eXBlIjoiaW5zdGl0dXRpb24iLCJsb2dvdXRfbGluayI6Imh0dHBzOi8vY29ubmVjdC5saWJseW54LmNvbS9sb2dvdXQvNjdmNThlNTA5ODI1OTAyZDNmYjNhYjZhODBhN2U4OTUiLCJhdXRoX21ldGhvZCI6ImlwIiwiaXAiOiI2Ni4yNDkuNzkuMTk3In1dLCJpYXQiOjE3NDQxNDcyMjEsImV4cCI6MTc0NTM1NjgyMX0.JJBcYOi9v5HvbvvbyxQ0RCFNHyol0jgEDaXBroqjZIw

[^2_5]: https://docs.redhat.com/en/documentation/red_hat_build_of_quarkus/2.2/html-single/deploying_your_quarkus_applications_to_openshift_container_platform/index

[^2_6]: https://github.com/wpernath/quarkus-simple

[^2_7]: https://www.wallarm.com/cloud-native-products-101/kubernetes-vs-openshift-deployment-and-management

[^2_8]: https://www.redhat.com/en/blog/your-guide-to-continuous-delivery-with-openshift-gitops-and-kustomize

[^2_9]: https://docs.redhat.com/en/documentation/red_hat_build_of_quarkus/3.20/pdf/deploying_your_red_hat_build_of_quarkus_applications_to_openshift_container_platform/Red_Hat_build_of_Quarkus-3.20-Deploying_your_Red_Hat_build_of_Quarkus_applications_to_OpenShift_Container_Platform-en-US.pdf

[^2_10]: https://www.youtube.com/watch?v=gyOe-m-kltw

[^2_11]: https://kccnceu20.sched.com/list/descriptions/

[^2_12]: https://github.com/quarkusio/quarkus/issues/32135

[^2_13]: https://eclipse.dev/jkube/docs/kubernetes-maven-plugin/

[^2_14]: https://www.youtube.com/watch?v=tsxDjR1asMA

[^2_15]: https://substack.com/home/post/p-158836146

[^2_16]: https://cloudowski.com/articles/10-most-important-differences-between-openshift-and-kubernetes/

[^2_17]: http://haralduebele.github.io/2020/04/03/deploy-your-quarkus-applications-on-kubernetes-almost-automatically/

[^2_18]: https://www.devopschina.org/wp-content/uploads/2023/08/GitOps-Cookbook-Red-Hat-Developer-ebook.pdf

[^2_19]: https://quarkus.io/guides/deploying-to-openshift

[^2_20]: https://www.scribd.com/document/726096133/ansible-for-kubernetes-by-example-automate-your-kubernetes-cluster-with-ansible-9781484292846-1484292847

