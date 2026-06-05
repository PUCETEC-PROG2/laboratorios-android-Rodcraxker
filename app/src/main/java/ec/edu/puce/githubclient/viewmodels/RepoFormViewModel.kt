package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.puce.githubclient.models.RepositoryPayload
import ec.edu.puce.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoFormViewModel: ViewModel() {
    private val _isLoading = MutableStateFlow( false)
    val isLoading: StateFlow<Boolean> =_isLoading.asStateFlow()
    private val _isSuccess = MutableStateFlow (false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()
    private val _errorMsg = MutableStateFlow<String?> (null)
    val errMsg: StateFlow<String?> = _errorMsg.asStateFlow()

    fun createRepository(name: String, description: String? ){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                val repositoryBody = RepositoryPayload(name, description)
                RetrofitClient.apiService.createRepository(repository = repositoryBody)
                _isSuccess.value = true
            }catch (e: Exception){
                _isSuccess.value = false
                _errorMsg.value = "Error al crear repositorio: ${e.localizedMessage}"

            }finally {
                _isLoading.value = false

            }
        }
    }
    fun resetSucess(){
        _isSuccess.value = false
    }

    fun resetError(){
        _errorMsg.value = null
    }

    // METODO UPDATE EN REPOFORMVIEWMODEL
    fun updateRepository(owner: String, repoName: String, payload: RepositoryPayload) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitClient.apiService.updateRepository(owner, repoName, payload)
                // Manejar éxito (ej. navegar atrás)
                _isSuccess.value = true
            } catch (e: Exception) {
                _errorMsg.value = "Error al actualizar: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}