package io.vertx.config.impl.spi;

import io.vertx.config.spi.utils.JsonObjectHelper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.config.spi.ConfigStore;

/**
 * An implementation of configuration store just retrieving the passed json object.
 * This store implementation is useful to support the `conf` parameter used by the Launcher class.
 *
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class JsonConfigStore implements ConfigStore {

  private JsonObject config;

  public JsonConfigStore(JsonObject configuration) {
    config = configuration;
  }

  @Override
  public void get(Handler<AsyncResult<Buffer>> completionHandler) {
    if (config == null) {
      completionHandler.handle(Future.failedFuture("no configuration"));
    } else {
      completionHandler.handle(Future.succeededFuture(JsonObjectHelper.toBuffer(config)));
    }
  }
}
