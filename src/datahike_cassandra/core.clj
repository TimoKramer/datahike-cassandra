(ns datahike-cassandra.core
  (:require [datahike.store :refer [empty-store delete-store connect-store default-config config-spec release-store store-identity]]
            [datahike.config :refer [map-from-env]]
            [konserve-cassandra.core :as k]
            [clojure.spec.alpha :as s]))

(defmethod store-identity :cassandra [store-config]
  (let [{:keys [session-keyspace contact-points]} store-config]
      [:cassandra session-keyspace contact-points]))

(defmethod empty-store :cassandra [store-config]
  (k/connect-store (dissoc store-config :backend)))

(defmethod delete-store :cassandra [store-config]
  (try
    (k/delete-store (dissoc store-config :backend))
    (catch Exception _e)))

(defmethod connect-store :cassandra [store-config]
  (k/connect-store (dissoc store-config :backend)))

(defmethod default-config :cassandra [config]
  (merge
   (map-from-env :datahike-store-config {:session-keyspace "konserve"})
   config))

(s/def :datahike.store.cassandra/session-keyspace string?)
(s/def ::cassandra (s/keys :req-un [:datahike.store.cassandra/session-keyspace]))

(defmethod config-spec :cassandra [_] ::cassandra)

(defmethod release-store :cassandra [_ store]
  (k/release store {:sync? true}))
