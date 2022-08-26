import 'package:flutter/material.dart';

import 'package:native_modules/batery/batery_widgte.dart';

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        useMaterial3: true,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Batery Module'),
        ),
        body: const BateryModule(),
      ),
    );
  }
}
