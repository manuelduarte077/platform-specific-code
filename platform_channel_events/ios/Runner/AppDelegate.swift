import UIKit
import Flutter
import Reachability

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
    
    private var reachability = try! Reachability()
    private let networkEventChannel = "platform_channel_events/connectivity"
    private let imageEventChannel = "platform_channel_events/image"

    override func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        let controller = window?.rootViewController as! FlutterViewController
        FlutterEventChannel(name: networkEventChannel, binaryMessenger: controller.binaryMessenger)
                    .setStreamHandler(NetworkStreamHandler(reachability: reachability))

        FlutterEventChannel(name: imageEventChannel, binaryMessenger: controller.binaryMessenger)
            .setStreamHandler(ImageStreamHandler())

        GeneratedPluginRegistrant.register(with: self)
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }
    
}
