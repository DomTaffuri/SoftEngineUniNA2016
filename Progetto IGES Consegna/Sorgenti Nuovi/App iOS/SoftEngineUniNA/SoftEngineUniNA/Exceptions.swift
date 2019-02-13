import Foundation
import UIKit

enum LoginExceptions : Error{
    case textFieldsEmpty
    case spaceInText
}

enum InviaDatiExceptions : Error{
    case letturaTextFieldEmpty
    case letturaWithChar
    case spaceInTextField
}
