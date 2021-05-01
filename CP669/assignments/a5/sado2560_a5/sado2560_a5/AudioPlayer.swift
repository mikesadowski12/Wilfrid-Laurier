//
//  AudioPlayer.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-17.
//

import UIKit;
import AVFoundation;

class AudioPlayer: NSObject {
    static let audioPlayer = AudioPlayer();
    
    private var backgroundMusicPlayer: AVAudioPlayer?;
    private var effectPlayer: AVAudioPlayer?;
    
    func playBackgroundMusic() {
        let sound = Bundle.main.path(forResource: "spaceinvaders-background", ofType: "mp3");
        
        do {
            try backgroundMusicPlayer = AVAudioPlayer(contentsOf:  URL(fileURLWithPath: sound!));
        } catch {
            print("Could  not create audio player");
        }
                
        backgroundMusicPlayer!.numberOfLoops = -1
        backgroundMusicPlayer!.prepareToPlay()
        backgroundMusicPlayer!.play()
    } //playBackgroundMusic
    
    func stopBackgroundMusic() {
        if (backgroundMusicPlayer != nil) {
            backgroundMusicPlayer!.stop()
        }
    }
    
    func playSoundEffect(filename: String, fileExtension: String) {
        let sound = Bundle.main.path(forResource: filename, ofType: fileExtension);
        
        do {
            try effectPlayer = AVAudioPlayer(contentsOf:  URL(fileURLWithPath: sound!));
        } catch {
            print("Could  not create audio player");
        }
                
        effectPlayer!.numberOfLoops = 0;
        effectPlayer!.prepareToPlay();
        effectPlayer!.play();
    }
}
