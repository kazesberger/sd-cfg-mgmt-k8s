# plan

## TODOs as of friday afternoon

* [ ] address TODOs in slides code
* [x] upload slides (gh-pages)
* [ ] and/or render pdf (consider https://github.com/hakimel/reveal.js/)

## notes

* tweet public slide deck (calm the niggrs #pulpfiction by mentioning/showing this on one of first and last slides)
* ask for feedback (upfront) -> blog post
* 

## topics

* [local kubeconfig](https://medium.com/@ahmetb/mastering-kubeconfig-4e447aa32c75)
* useful links
* category distinction: bespoken vs COTS (common off the shelf) apps
* list of k8s app mgmt tools
* helm? -> not that different to kapitan but less complicated
* xxx as Code?
* template yaml?
* patching manifests?
* patching helm charts? https://testingclouds.wordpress.com/2018/07/20/844/
* kapitan
* kr8
* ship
* state vs statless apps -> operators

## ablauf

//* not battle-tested knowledge -> more like ivory tower -> more like hyde park speakers corner speaker
* k8s introduction like talk summary
* we don't like problems so let's start positive: what do i appreciate about k8s up to now:
* kubectl commands imparative vs declarative -> kubectl apply -> yay (make it so!)
    * https://kubernetes.io/docs/concepts/overview/object-management-kubectl/overview/
* forget about infra -> manipulate state by declaration of desired state -> git -> operate by PR -> gitops
* we like this declarative thing - let's not lose it on our way
* well this sounds easy, until -> Wall of Yaml-files crits over 9k
    * maybe give a short overview of loc for manifest files of some COTS services
    * but this isn't where it ends, we might have multiple (slightly different) instances of the same thing

    * so we need something like an inventory, config that makes our app instances special.
    * we want to avoid copy/pasting files (while slightly modifiying them)
    * we want to preserve the ability to easily upgrade the core parts (eg. rebase on new upstream version)
* downsides of templating (like in helm/values.yaml or by jsonnet-based tools)
    * we need to build the template (bespoken vs COTS)
    * we need to provide the values
        * dealing with optional values
        * dealing with conditions
* perks of patching with kustomize?
    * kustomize works with the original manifest files - we can work with unmodified upstream and easily upgrade anytime
* combined powers #cptnPlanet -> kapitanOverlayed
    * inventory: reclass
    * we only generate values.yaml and/or kustomize patches
    * thus we can easily rebase new upstream versions anytime

* so now we've got a workflow
* does it still work for stateful service operations world?
    * name examples of popular/common services
    * so we have situations where we need a special procedure to establish desired state
    * and we might even have a somewhat dynamic desired state.
* operators:
    * a design pattern built upon the controller pattern
    ```
    In applications of robotics and automation, a control loop is a non-terminating loop 
    that regulates the state of the system. In Kubernetes, a controller is a control loop 
    that watches the shared state of the cluster through the API server and makes changes 
    attempting to move the current state towards the desired state. Examples of controllers 
    that ship with Kubernetes today are the replication controller, endpoints controller, 
    namespace controller, and serviceaccounts controller.
    ```
    * an operator 
        * watches
        * analyzes
        * acts
    * and the operator does this
        * with single-app focus
        * utilizing CRDs
        
        
        
        
* k8s introduction (fast lane)
    * illustrated children's guide to k8s (phippy)
    * active management of Cluster to monitor and deliver desired state
        * Scheduling (eg. replication Controller)
        * Networking
        * Services
        * Storage Backends
* declarative config
* DRY
* upgrades
* 
