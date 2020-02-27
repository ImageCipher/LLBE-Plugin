package com.imagecipher.llbeplugin;

import com.imagecipher.icsdk.IcPlugin;
import com.imagecipher.icsdk.PluginInstance;

public class LLBEPlugin implements IcPlugin {

    @Override
    public PluginInstance onPluginLoaded() {
        System.out.println("LLBE Plugin loaded");

        PluginInstance instance = new PluginInstance();
        instance.getEncrypters().add(new Encrypter());

        return instance;
    }

    @Override
    public void onPluginShutdown() {
        System.out.println("LLBE Plugin shutdown");
    }
}
