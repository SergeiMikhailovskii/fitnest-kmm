import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greeting()
    
    @State private var username: String = ""
    
    var body: some View {
        TextField("test", text: $username)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
