package com.quoteapp;

import com.quoteapp.resources.QuoteAppResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class QuoteAppApplication extends Application<QuoteAppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new QuoteAppApplication().run(args);
    }

    @Override
    public String getName() {
        return "quote-app";
    }

    @Override
    public void initialize(final Bootstrap<QuoteAppConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html", "root"));
        bootstrap.addBundle(new AssetsBundle("/assets/css/", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js/", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/lib/", "/lib", null, "lib"));
    }

    @Override
    public void run(final QuoteAppConfiguration configuration,
                    final Environment environment) {
        final QuoteAppResource resource = new QuoteAppResource(
                configuration.getQuotes()
            );
        environment.jersey().register(resource);
    }

}
