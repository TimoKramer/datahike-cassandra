(ns datahike-cassandra.core
  (:require [datahike.store :refer [empty-store
                                    delete-store
                                    connect-store
                                    scheme->index
                                    default-config
                                    config-spec]]
            [hitchhiker.tree.bootstrap.konserve :as kons]
            [konserve-cassandra.core :as k]
            [environ.core :refer [env]]
            [clojure.spec.alpha :as s]
            [superv.async :refer [<?? S]]))

(defmethod empty-store :cassandra [config]
  (kons/add-hitchhiker-tree-handlers
   (<?? S (k/new-store config))))

(defmethod delete-store :cassandra [config]
  (let [conn (<?? S (k/new-store config))]
    (<?? S (k/delete-store conn))))

(defmethod connect-store :cassandra [config]
  (<?? S (k/new-store config)))

(defmethod scheme->index :cassandra [_]
  :datahike.index/hitchhiker-tree)

(defmethod default-config :cassandra [config]
  (merge
    {:session-keyspace "datahike"
     :contact-points (get env :datahike-store-contact-points "127.0.0.1")}
    config))

(s/def :datahike.store.cassandra/session-keyspace string?)
(s/def :datahike.store.cassandra/contact-points (s/coll-of string?))
(s/def ::cassandra (s/keys :req-un [:datahike.store.cassandra/session-keyspace
                                    :datahike.store.cassandra/contact-points]))

(defmethod config-spec :cassandra [_] ::cassandra)

(comment
  (def config { :session-keyspace "konserve"
                :contact-points ["127.0.0.1"]})

  (<?? S (k/new-store config)))
