package com.pichillilorenzo.flutter_appavailability;

import android.content.Context;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class AppAvailability implements FlutterPlugin {
    private static final String CHANNEL_NAME = "com.pichillilorenzo/flutter_appavailability";
    private MethodChannel channel;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        final AppAvailability plugin = new AppAvailability();
        plugin.setupChannel(registrar.messenger(), registrar.context());
    }

    @Override
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        setupChannel(binding.getBinaryMessenger(), binding.getApplicationContext());
    }

    @Override
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        teardownChannel();
    }

    private void setupChannel(BinaryMessenger messenger, Context context) {
        channel = new MethodChannel(messenger, CHANNEL_NAME);
        MethodCallHandlerImpl handler = new MethodCallHandlerImpl(context);
        channel.setMethodCallHandler(handler);
    }

    private void teardownChannel() {
        channel.setMethodCallHandler(null);
        channel = null;
    }
}
