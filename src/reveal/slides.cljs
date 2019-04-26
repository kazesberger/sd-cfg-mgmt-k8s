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

(def intro
  [:section
   [:h3 "this is how you DONT do presentations:"]])

(def intro-2
  [:section
   [:p "This talk provides a beginner friendly introduction to the options available in the space of Kubernetes Configuration Management.\n  Kubernetes Configuration management is declarative and quite explicit. Also the Kubernetes API is extensive.\n  A valid Pod spec contains at least 30 distinct, valid child attributes (k8s v1.13).\n  There is a good chance specs will grow in their number and by numbers of fields.\n  This talk will give a short overview of the problem space and current options to manage configuration changes."]])

(def intro-positive
  [:section
   [:h3 "things i like about k8s:"]
   [:p
    (bulletpoints
      ["declarative"
       "kubectl apply"])]
   [:img {:src "img/make-it-so.jpg" :class "fragment" :style "max-height:300px"}]])

(def controller-first-encounter
  [:section
   [:p "we declare stuff, then..."]
   [:img {:src "img/magical-dr-evil.jpg"}]
   [:p "...now the state is as we desired?"]])

(def controllers-alien-meme
  [:section
   [:img {:src "img/controllers-alien.jpg"}]])

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
   [:h3 "k8s object management"]
   [:table {:style "white-space:nowrap; width:auto;"}
    [:thead {:style "font-weight: bold;"}
     [:th "technique"]
     [:th "example"]]
    [:tbody
     [:tr [:td "imp cmd"] [:td "kubectl run nginx --image nginx"]]
     [:tr [:td "imp obj config"] [:td "kubectl create -f nginx.yaml"]]
     [:tr [:td ""] [:td "kubectl delete -f nginx.yaml"]]
     [:tr [:td ""] [:td "kubectl replace -f nginx.yaml"]]
     [:tr [:td "decl obj config"][:td "kubectl apply -R -f configs/"]]]]])

(def imparative-vs-declarative-3
  [:section
   [:h3 "recap: declarative cfg"]
   (bulletpoints
     ["forget about the HOW, the parts and infra"
      "manipulate state by declaration of desired state"
      "utilize git for these declarations"
      "PR-Workflow gives you 'GitOps'"
      "we like this - let's not lose it on our journey"])])

(def sounds-ez
  [:section {:class "fragment"}
   [:h4 "sounds doable!"]
   [:ol
    [:li {:class "fragment"} "Write the App"]
    [:li {:class "fragment"} "Describe its parts using 'Kubernetes-Objects'"]
    [:li {:class "fragment"} "kubectl apply -f config/"]]
   [:img {:src "img/loc-msvc-demo.png" :class "fragment"}]])

(def fun-begins-DRY
  [:section
   [:h4 "what if..."
     [:p "Stages"]
     [:img {:src "img/stages.png"}]]])

(def fun-begins-DRY-2
  [:section
   [:h4 "what if..."
    [:p "customizations"]
    [:p [:img {:src "img/gopher-orig.png" :style "max-width:120px"}]]

    [:p
     [:img {:src "img/gopher-fabulous-viking.png" :style "max-width:120px; margin:30px"}]
     [:img {:src "img/gopher-cptn-death-docker.png" :style "max-width:120px; margin:10px"}]
     [:img {:src "img/gopher-hipster.png" :style "max-width:120px; margin:30px"}]]]])

(def duplications-DRY
  [:section
   [:img {:src "img/stages-n-customizations.png"}]])

(def DRY-make-point
  [:section
   [:h4 "My point here is... DRY"]
   (bulletpoints
     ["face full of yaml"
      "...very similar yaml"
      "duplication is bad"])])

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


(defn all
  "Add here all slides you want to see in your presentation."
  []
  [title-slide
   intro
   intro-2
   intro-positive
   controller-first-encounter
   controllers-alien-meme
   imparative-vs-declarative
   imparative-vs-declarative-2
   imparative-vs-declarative-3
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