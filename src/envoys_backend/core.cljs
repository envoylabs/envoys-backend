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
  {:hero-text "A functional-first engineering collective with a focus on doing things right, first time."
   :body-text "We specialise in green-field and rapid prototyping work, from front-end and static lean MVPs to full-stack proof of concepts. Whether it's engineering, devops or business development, we can help with all areas of the project build and lifecycle."
   :services-list ["Full-stack software engineering"
                   "Serverless applications"
                   "Data engineering"
                   "Prototyping and MVPs"
                   "Delivery support"
                   "Architecture and design"
                   "User engagement and research"
                   "Workshops and training"]
   :technologies ["Clojure"
                  "Javascript"
                  "Ruby"
                  "Docker"
                  "Kubernetes"
                  "AWS"
                  "GCP"
                  "Apache Kafka"
                  "Apache Spark"
                  "Neo4j"
                  "MongoDB"
                  "PostgreSQL"
                  "Node.js"
                  "DAT"
                  "Blockchain"
                  "Linked data"
                  "GraphQL"]})

(def about-data
  {:hero-text "About"
   :body-text "Lorem Ipsum"})

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
