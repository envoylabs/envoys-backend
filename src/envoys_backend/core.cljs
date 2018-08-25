(ns envoys-backend.core
  (:require [cljs-lambda.macros :refer-macros [deflambda defgateway]]
            [cljs-lambda.local :as local]
            [cljs-lambda.aws.event :as awse]))

(defn wrap-layout [map status]
  "Wrap the passed-in body map with the other parts of the json map
   that we want to return."
  (let [context {:status status
                 :headers {:content-type "application/json"}}]
    (-> map
        (merge context))))

(defn wrap-common [map]
  "UI components common to all endpoints"
  (-> map
      (merge {:title "Envoys | Software Engineering"})))

(def index-data
  {:hero-text "A functional-first engineering collective that does things right, first time."})

(def about-data
  {:hero-text "We specialise in green-field and rapid prototyping, from static MVPs to full-stack apps and data systems. Whether it's engineering, devops or business development, we can help with all areas of the project lifecycle."})

(def blog-data
  {:hero-text "Under construction..."})

(def contact-data
  {:hero-text "For general queries, drop us a line at hello@envoys.io. To discuss a project with our CTO, email alex@lynh.am"})

(defn body->layout-as-json [data-map]
  (let [js-map (-> data-map
                   wrap-common
                   clj->js)]
    (.stringify js/JSON js-map)))

(defgateway index [event ctx]
  (let [body {:body (body->layout-as-json index-data)}]
    (wrap-layout body 200)))

(defgateway about [event ctx]
  (let [body {:body (body->layout-as-json about-data)}]
    (wrap-layout body 200)))
