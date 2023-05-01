import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_sound_lite/flutter_sound.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatefulWidget {
  const MainApp({super.key});

  @override
  State<MainApp> createState() => _MainAppState();
}

class _MainAppState extends State<MainApp> {
  List ringtones = [];
  var player = FlutterSoundPlayer();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        useMaterial3: true,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Native Ringtone'),
          elevation: 0,
        ),
        body: Column(
          children: [
            if (ringtones.isNotEmpty)
              Expanded(
                child: ListView.builder(
                  shrinkWrap: true,
                  itemCount: ringtones.length,
                  itemBuilder: (context, index) {
                    final ringtone = ringtones[index];
                    return Card(
                      elevation: 0,
                      color: Colors.deepPurple[50],
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(8),
                      ),
                      child: ListTile(
                        onTap: () async {
                          player = (await player.openAudioSession())!;
                          player.startPlayer(
                            fromURI:
                                '/system/media/audio/ringtones/$ringtone.ogg',
                            codec: Codec.opusOGG,
                            whenFinished: () {
                              player.stopPlayer();
                            },
                            fromDataBuffer: null,
                          );
                        },
                        title: Text(ringtone),
                        subtitle: ringtone.contains('notification')
                            ? const Text('Notification')
                            : const Text('Ringtone'),
                        trailing: const Icon(Icons.play_arrow),
                      ),
                    );
                  },
                ),
              ),
            Center(
              child: FilledButton(
                onPressed: () async {
                  const channel = MethodChannel('native_ringtone');
                  ringtones = await channel.invokeMethod('getRingtones');

                  setState(() {
                    ringtones = ringtones;
                  });
                },
                child: const Text('Get Ringtones'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
