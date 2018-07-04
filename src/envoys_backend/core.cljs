(ns envoys-backend.core
  (:require [cljs-lambda.macros :refer-macros [deflambda defgateway]]
            [cljs-lambda.local :as local]
            [cljs-lambda.aws.event :as awse]))

(def page-structure
  {:title "Envoys | Software Engineering"
   :hero-text "A functional-first engineering collective with a focus on doing things right, first time."
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

(defgateway index [event ctx]
  {:status 200
   :headers {:content-type "application/json"}
   :body (-> page-structure
             clj->js
             (.stringify js/JSON))})


(deflambda lambda-adder [[a b] ctx]
  (+ a b))

(defn adder [a b]
  (+ a b))
