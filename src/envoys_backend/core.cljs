(ns envoys-backend.core
  (:require [cljs-lambda.macros :refer-macros [deflambda defgateway]]
            [cljs-lambda.local :as local]
            [cljs-lambda.aws.event :as awse]
            ["aws-sdk" :as aws]))

(defn wrap-layout [map status]
  "Wrap the passed-in body map with the other parts of the json map
   that we want to return."
  (let [context {:status status
                 :headers {:content-type "application/json"
                           :access-control-allow-origin "*"
                           :access-control-allow-credentials "true"}}]
    (-> map
        (merge context))))

(defn wrap-common [map]
  "UI components common to all endpoints"
  (-> map
      (merge {:title "Envoys | Software Engineering"})))

;; refactor this into defmulti when we move to database
(def index-data
  {:hero-text "A functional-first engineering collective that does things right, first time."})

(def about-data
  {:hero-text "We specialise in green-field and rapid prototyping, from static MVPs to full-stack apps and data systems. Whether it's engineering, devops or business development, we can help with all areas of the project lifecycle."})

(def blog-data
  {:hero-text "Under construction..."})

(def contact-data
  {:hero-text "For general queries, drop us a line at hello@envoys.io. To discuss a project with our CTO, email alex@lynh.am"})

(defn clj->json [map]
  (let [js-map (-> map
                   clj->js)]
    (.stringify js/JSON js-map)))

(defn body->layout-as-json [data-map]
  (let [wrapped-map (-> data-map
                          wrap-common)]
    (clj->json wrapped-map)))

(defn get-env []
  "Returns current env vars as a Clojure map."
  (-> (.-env js/process)
      js->clj))

(defn get-sns-topic-from-env []
  (-> (get-env)
      (get "SNS_TOPIC")))

(defn get-sns-client []
  (aws/SNS.))

(defn event->sns-event [event]
  (let [sms-msg-generator #({:default %
                             :sms %})]
    (-> event
        :body
        sms-msg-generator
        clj->json)))

(defn send-to-sns [sns-client topic subject msgs]
  "Send message to an SNS topic"
  (let [publish-fn (fn [msg topic]
                     (.publish sns-client {:Message msg
                                           :MessageAttributes {:DataType "String"}
                                           :MessageStructure "json"
                                           :TopicArn topic
                                           :Subject subject}))]
    (map publish-fn msgs)))

;; any fatal errors will be caught by Lambda invocation
;; and wrapped by API Gateway, so we only return 200
(defgateway index [event ctx]
  (let [body {:body (body->layout-as-json index-data)}]
    (wrap-layout body 200)))

(defgateway about [event ctx]
  (let [body {:body (body->layout-as-json about-data)}]
    (wrap-layout body 200)))

(defgateway blog [event ctx]
  (let [body {:body (body->layout-as-json blog-data)}]
    (wrap-layout body 200)))

(defgateway contact [event ctx]
  (let [body {:body (body->layout-as-json contact-data)}]
    (wrap-layout body 200)))

(defgateway contact-form [event ctx]
  (let [topic (get-sns-topic-from-env)
        sns-client (get-sns-client)
        sns-messages (into [] (event->sns-event event))
        response (send-to-sns sns-client topic "Contact Form" sns-messages)
        body {:body {:msg "success"}} ;; todo - reduce responses and filter
        status 200]
    (wrap-layout body status)))

