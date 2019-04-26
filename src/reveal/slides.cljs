(ns reveal.slides)

(defn bulletpoints [items]
  (let [li-attributes {:class "fragment"}]
    (vec (concat [:ul] (map #(vector :li li-attributes %) items)))))

(defn note [s]
  [:aside {:class "notes"} s])

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
   (bulletpoints
     ["declarative"
      "kubectl apply"
      [:img {:src "img/make-it-so.jpg"}]])])

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

(def fun-begins ; TODO make these pictures not bulletpoints
  [:section
   [:h4 "what if..."]
   (bulletpoints
     [[:img {:src "img/stages.png"}]
      "Customizations"
      "..."])])

(def copy-pasta
  [:section
   [:img {:src "img/inventory-1.png"}]])

(def templates
  [:section
   [:pre
     "apiVersion: v1\nkind: Service\nmetadata:\n  name: {{ template \"minecraft.fullname\" . }}\n  labels:\n    app: {{ template \"minecraft.fullname\" . }}\n    chart: \"{{ .Chart.Name }}-{{ .Chart.Version }}\"\n    release: \"{{ .Release.Name }}\"\n    heritage: \"{{ .Release.Service }}\"\nspec:\n  type: {{ .Values.minecraftServer.serviceType }}\n  ports:\n  - name: minecraft\n    port: 25565\n    targetPort: minecraft\n    protocol: TCP\n  selector:\n    app: {{ template \"minecraft.fullname\" . }}"]
    ;[:code {:class "hljs" :data-trim "" :data-noescape ""} "apiVersion: v1\nkind: Service\nmetadata:\n  name: {{ template \"minecraft.fullname\" . }}\n  labels:\n    app: {{ template \"minecraft.fullname\" . }}\n    chart: \"{{ .Chart.Name }}-{{ .Chart.Version }}\"\n    release: \"{{ .Release.Name }}\"\n    heritage: \"{{ .Release.Service }}\"\nspec:\n  type: {{ .Values.minecraftServer.serviceType }}\n  ports:\n  - name: minecraft\n    port: 25565\n    targetPort: minecraft\n    protocol: TCP\n  selector:\n    app: {{ template \"minecraft.fullname\" . }}"]]
   [:img {:src "img/helm-template"}]])

(def template-downsides
  [:section
   [:h4 "downsides of templating"]
   (bulletpoints
     ["we need to change the manifests"
      "we need to provide the values"])])

; TODO short helm explanation and show templates
; TODO show folder full of values.yaml files (lack of proper inventory)

(def template-downsides-no-big-deal-aight
  [:section
   [:h4 "change the manifests?"
    (bulletpoints
      ["bespoken apps"
       "Common Off-The-Shelf (COTS) apps"])]])


(def kustomize
  [:section
   [:h4 "kustomize to the rescue"]
   (bulletpoints
     ["templating without placeholders"
      "this ain't templating!!!11"
      "...that's a good thing :)"
      "but we're still having "])])

(def controller-first-encounter
  [:section
   [:p "we declare stuff, then..."]
   [:img {:src "img/magical-dr-evil.jpg"}]
   [:p "the state is as we desired?"]])

(def controllers-alien-meme
  [:section
   [:img {:src "img/controllers-alien.jpg"}]])

(def cots-and-bespoken-apps)


(defn all
  "Add here all slides you want to see in your presentation."
  []
  [title-slide
   intro
   intro-2
   intro-positive
   imparative-vs-declarative
   imparative-vs-declarative-2
   imparative-vs-declarative-3
   sounds-ez
   fun-begins
   copy-pasta
   templates])

   ;controller-first-encounter
   ;controllers-alien-meme])