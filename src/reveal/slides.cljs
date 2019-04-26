(ns reveal.slides)

(defn bulletpoints [items]
  (let [li-attributes {:class "fragment"}]
    (vec (concat [:ul] (map #(vector :li li-attributes %) items)))))

(defn note [component]
  [:aside {:class "notes"} component])

(def title-slide
  [:section
   [:h1 "K8s Config Mgmt"]
   [:h3 "Beginners guide"]])

;(def intro
;  [:section
;   [:h3 "this is how you DONT do presentations:"]])

(def intro-2
  [:section
   (note "!read! then -> 'but i want to be more positive about this exciting topic'")
   [:h3 "the talk description says"]
   [:p "[..] This talk will give a short overview of the problem space and current options to manage configuration changes."]])

(def intro-positive
  [:section
   (note [:div
          [:p "declarative: we tell k8s what we desire and k8s acts to transform current => desired state"]
          [:p "we tell k8s, we want it to have 'things' -> objects"]
          [:p "we can do this w/ my fav cmd 'kb apply'"]
          [:p "make it so"]
          [:p "k8s needs to know what we're talking about -> resources"]])
   [:h3 "things i like about k8s:"]
   [:p
    (bulletpoints
      ["declarative"
       "kubectl apply"])]
   [:img {:src "img/make-it-so.jpg" :class "fragment" :style "max-height:300px"}]])

(def intro-of-resources-and-objects
  [:section
   (note
     [:div
      [:p "example: object of type/kind 'Deployment', a resource known to k8s"]])
   [:pre "apiVersion: apps/v1\nkind: Deployment\nmetadata:\n  name: nginx-deployment\n  labels:\n    app: nginx\nspec:\n  replicas: 3\n  selector:\n    matchLabels:\n      app: nginx\n  template:\n    metadata:\n      labels:\n        app: nginx\n    spec:\n      containers:\n      - name: nginx\n        image: nginx:1.7.9\n        ports:\n        - containerPort: 80\n"]])

(def controller-first-encounter
  [:section
   [:p "we declare stuff, then..."]
   [:img {:src "img/magical-dr-evil.jpg"}]
   [:p "...now the state is as we desired?"]])

(def controllers-alien-meme
  [:section
   [:img {:src "img/controllers-alien.jpg"}]])

(def controllers-simplified
  [:section
   {:data-background-image "img/controllers-simplified.png" :data-background-size "contain"}
   [:p " "]])
   ;[:img {:src "img/controllers-simplified.png"}]])

[:p "k8s is extensible. for special things:  custom resource definitions"]

(def imparative-vs-declarative
  [:section
   [:h3 "k8s object management"]
   [:table {:style "white-space:nowrap;"}
    [:thead {:style "font-weight: bold;"}
     [:th "technique"]
     [:th "operates on"]
     [:th "learning curve"]]
    [:tbody
     [:tr [:td "imparative commands"][:td "live objects"][:td "lowest"]]
     [:tr [:td "imperative obj config"][:td "individual files"][:td "moderate"]]
     [:tr [:td "declarative obj config"][:td "Directories of files"][:td "highest"]]]]])

(def imparative-vs-declarative-2
  [:section
   [:section
    [:h3 "k8s object management"]
    [:h5 "imperative vs declarative"]
    [:table {:style "white-space:nowrap; width:auto;"}
     [:thead {:style "font-weight: bold;"}
      [:th "technique"]
      [:th "example"]]
     [:tbody
      [:tr [:td "imp cmd"] [:td "kubectl run nginx --image nginx"]]
      [:tr [:td "imp obj config"] [:td "kubectl create -f nginx.yaml"]]
      [:tr [:td ""] [:td "kubectl delete -f nginx.yaml"]]
      [:tr [:td ""] [:td "kubectl replace -f nginx.yaml"]]
      [:tr [:td "decl obj config"] [:td "kubectl apply -R -f configs/"]]]]]
   imparative-vs-declarative])

(def imparative-vs-declarative-3
  [:section
   [:h3 "recap: declarative cfg"]
   (bulletpoints
     ["forget about the HOW, the parts and infra"
      "manipulate state by declaration of desired state"])])
(def imparative-vs-declarative-4
  [:section
   [:h3 "what we gain"]
   (bulletpoints
     ["git ❤ declarative approach"
      "PR-Workflow gives you 'GitOps'"])])
   ;[:p {:class "fragment"} "we like this - let's not lose it on our journey"]])

(def sounds-ez
  [:section {:class "fragment"}
   [:h3 "sounds doable!"]
   [:ol
    [:li {:class "fragment"} "Write the App"]
    [:li {:class "fragment"} "Describe its parts using 'Kubernetes-Objects'"]
    [:li {:class "fragment"} "kubectl apply -f config/"]]
   [:img {:src "img/loc-msvc-demo.png" :class "fragment"}]])

(def fun-begins-DRY
  [:section
   [:h3 "what if..."]
   [:p "Stages"]
   [:img {:src "img/stages.png"}]])

(def fun-begins-DRY-2
  [:section
   [:h3 "what if..."]
   [:p "Customizations"]
   [:p [:img {:src "img/gopher-orig.png" :style "max-width:120px"}]]

   [:p
    [:img {:src "img/gopher-fabulous-viking.png" :style "max-width:120px; margin:30px"}]
    [:img {:src "img/gopher-cptn-death-docker.png" :style "max-width:120px; margin:10px"}]
    [:img {:src "img/gopher-hipster.png" :style "max-width:120px; margin:30px"}]]])

(def duplications-DRY
  [:section
   [:img {:src "img/stages-n-customizations.png"}]])

(def DRY-make-point
  [:section ;TODO add missing notes starting from here
   (note "")
   [:h4  "My point here is..."]
   [:h3 "DRY"]
   (bulletpoints
     ["face full of yaml"
      "...very similar yaml"
      "duplication is bad"])])

; TODO add more visual stuff and examples

(def templates-by-helm-example
  [:section
   [:h3 "Templates (Helm example)"]
   [:pre
    "apiVersion: v1\nkind: Service\nmetadata:\n  name: {{ template \"minecraft.fullname\" . }}\n  labels:\n    app: {{ template \"minecraft.fullname\" . }}\n    chart: \"{{ .Chart.Name }}-{{ .Chart.Version }}\"\n    release: \"{{ .Release.Name }}\"\n    heritage: \"{{ .Release.Service }}\"\nspec:\n  type: {{ .Values.minecraftServer.serviceType }}\n  ports:\n  - name: minecraft\n    port: 25565\n    targetPort: minecraft\n    protocol: TCP\n  selector:\n    app: {{ template \"minecraft.fullname\" . }}"]])
   ;[:code {:class "hljs" :data-trim "" :data-noescape ""} "apiVersion: v1\nkind: Service\nmetadata:\n  name: {{ template \"minecraft.fullname\" . }}\n  labels:\n    app: {{ template \"minecraft.fullname\" . }}\n    chart: \"{{ .Chart.Name }}-{{ .Chart.Version }}\"\n    release: \"{{ .Release.Name }}\"\n    heritage: \"{{ .Release.Service }}\"\nspec:\n  type: {{ .Values.minecraftServer.serviceType }}\n  ports:\n  - name: minecraft\n    port: 25565\n    targetPort: minecraft\n    protocol: TCP\n  selector:\n    app: {{ template \"minecraft.fullname\" . }}"]]

(def templating-at-microsvc-demo
  [:section
   (note
     [:div
      [:pre "cd ~/git/sips/microservices-demo/deploy/kubernetes/helm-chart/templates"]
      [:pre "grep '{{' *"]
      [:pre "cd ~/git/sips/gitea-helm-chart/templates/gitea"]])
   [:h3 "example-config"]
   [:h4 "(sockshop / gitea)"]])

(def template-downsides
  [:section
   (note [:ul
          [:li "manifests -> upgrades"]
          [:li "values -> copy/pasta vs inventory"]])

   [:h3 "downsides of templating"]
   (bulletpoints
     ["we need to template (change) the manifests"
      "we need to provide the values"])])

(def what-we-want
  [:section
   (note
     [:p
      [:ul
       [:li "ez upgrades -> no path-overlap with upstream"]
       [:li "inventory -> duplication within value files"]]
      "next: can we apply templating to all our apps and workflows?"])
   [:h3 "Goals"]
   (bulletpoints
     ["DRY"
      "Easy upgrades"
      "Inventory (what makes app instances special)"])])

(def types-of-applications
  [:section
   (note
     [:p "can we apply templating to all our apps and workflows?"
      [:ul
       [:li "bespoke: 'build yourself', origins in clothing industry"]
       [:li "COTS: eg. Products from Atlassian stack"]]])
   [:h3 "kinds of apps and workflows"]
   (bulletpoints
     ["bespoke apps"
      "Common Off-The-Shelf (COTS) apps"])])

(def kustomize
  [:section
   [:h3 "kustomize"]
   (bulletpoints
     ["templating without placeholders"
      "kustomize knows where to put the values"
      "this ain't templating !!!11"
      "...that's a good thing :-)"])])

(def kustomize-examples-1)

(def workflows-bespoke
  [:section
   {:data-background-image "img/workflowBespoke.jpg" :data-background-size "contain"}
   [:p " "]])

(def workflows-cots
  [:section
   {:data-background-image "img/workflowOts.jpg" :data-background-size "contain"}
   [:p " "]])

; TODO operators

(defn all
  "Add here all slides you want to see in your presentation."
  []
  [title-slide
   ;intro
   intro-2
   intro-positive
   intro-of-resources-and-objects
   controller-first-encounter
   controllers-alien-meme
   controllers-simplified
   ;
   imparative-vs-declarative-2
   imparative-vs-declarative-3
   imparative-vs-declarative-4
   sounds-ez
   fun-begins-DRY
   fun-begins-DRY-2
   duplications-DRY
   DRY-make-point
   templates-by-helm-example
   templating-at-microsvc-demo
   template-downsides
   what-we-want
   types-of-applications
   kustomize
   workflows-bespoke
   workflows-cots])