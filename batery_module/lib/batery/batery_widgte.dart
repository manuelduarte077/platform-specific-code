import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BateryModule extends StatefulWidget {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  const BateryModule({Key? key}) : super(key: key);

  @override
  State<BateryModule> createState() => _BateryModuleState();
}

class _BateryModuleState extends State<BateryModule> {
  // Get battery level.
  String _batteryLevel = 'Unknown battery level.';

  Future<void> _getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result =
          await BateryModule.platform.invokeMethod('getBatteryLevel');
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
    }

    setState(() {
      _batteryLevel = batteryLevel;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          FilledButton(
            onPressed: _getBatteryLevel,
            child: const Text('Get Batery Level'),
          ),
          Text(_batteryLevel)
        ],
      ),
    );
  }
}
