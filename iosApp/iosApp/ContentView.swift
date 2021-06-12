import SwiftUI
import shared

struct ContentView: View {
    @State private var login: String = ""
    @State private var password: String = ""
    
    var body: some View {
        VStack(alignment: .center) {
            TextField("Login", text: $login).border(Color(UIColor.separator))
            TextField("Password", text: $password).border(Color(UIColor.separator))
            Button("Login") {
                LoginUseCase().save(data: LoginData(login: login, password: password))
            }
            Spacer()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
