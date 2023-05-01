import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatefulWidget {
  const MainApp({super.key});

  @override
  State<MainApp> createState() => _MainAppState();
}

class _MainAppState extends State<MainApp> {
  final _methodChannel = const MethodChannel('show_toast');

  showToast() {
    _methodChannel
        .invokeMethod('showToast', {'message': 'Manuel is the best!'});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      themeMode: ThemeMode.dark,
      darkTheme: ThemeData.dark(useMaterial3: true),
      home: Scaffold(
        appBar: AppBar(title: const Text('Show Toast')),
        body: Center(
          child: FilledButton(
            onPressed: showToast,
            child: const Text('Show Toast'),
          ),
        ),
      ),
    );
  }
}
