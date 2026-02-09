package gameCharacter

class GameCharacter(
    val name: String,
    private var _state: CharacterState = CharacterState.Idle
) {
    var state: CharacterState = _state
        private set

    fun changeState(newState: CharacterState) {
        state = newState
    }
}