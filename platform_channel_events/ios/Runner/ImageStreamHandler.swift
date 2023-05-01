
import Foundation

class ImageStreamHandler: NSObject, FlutterStreamHandler {
    
    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        let args = arguments as! Dictionary<String, Any>
        let quality = args["quality"] as! Double
        let chunkSize = args["chunkSize"] as! Int
        dispatchImageEvents(quality: quality, chunkSize: chunkSize, eventSink: events)
        return nil
    }
    
    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        return nil
    }
    
    func dispatchImageEvents(quality: Double, chunkSize: Int, eventSink: @escaping FlutterEventSink) -> Void {
        
        // Load the image into memory
        guard let image  = UIImage(named: "cute_dog_unsplash.jpg"),
              
        // Compress the image using the quality passed from Flutter
        let data = image.jpegData(compressionQuality: CGFloat(quality)) else {return}
        
        // Get the size of the image
        let length = data.count
        
        // Dispatch the first event (which is the size of the image)
        eventSink(length)
        
        DispatchQueue.global(qos: .background).async {
            
            // Split the image into chunks using the chunkSize passed from Flutter
            let parts = length / chunkSize
            var offset = 0
            
            // Repeat this block of statements until we have dispatched the last chunk
            repeat {
                // Mimic buffering with a 1 mill delay
                usleep(1000)
                
                let thisChunkSize = ((length - offset) > parts) ? parts : (length - offset)
                let chunk  = data.subdata(in: offset..<offset + thisChunkSize)
                
                // Dispatch each chunk to Flutter
                eventSink(chunk)
                offset += thisChunkSize
                
            } while (offset < length)
            
            // Dispatch an event to indicate the end of the stream
            eventSink(Constants.eof)
        }
        
    }

    
}
