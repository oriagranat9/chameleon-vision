package com.chameleonvision;

import com.chameleonvision.common.configuration.CameraConfiguration;
import com.chameleonvision.common.configuration.ChameleonConfiguration;
import com.chameleonvision.common.configuration.ConfigManager;
import com.chameleonvision.common.vision.processes.VisionModuleManager;
import com.chameleonvision.common.vision.processes.VisionSource;
import com.chameleonvision.common.vision.processes.VisionSourceManager;
import com.chameleonvision.server.Server;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConfigManager configManager = ConfigManager.getInstance();
        configManager.load();
        ChameleonConfiguration chameleonConfiguration = configManager.getConfig();
        List<CameraConfiguration> configurationList =
                new ArrayList<>(chameleonConfiguration.getCameraConfigurations().values());
        List<VisionSource> visionSources = new VisionSourceManager().LoadAllSources(configurationList);
        VisionModuleManager moduleManager = new VisionModuleManager(visionSources);
        moduleManager.startModules();

        Server.main(80);
    }
}
