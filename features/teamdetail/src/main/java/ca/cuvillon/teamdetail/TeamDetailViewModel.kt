package ca.cuvillon.teamdetail

import ca.cuvillon.common.base.BaseViewModel
import ca.cuvillon.repository.AppDispatchers
import ca.cuvillon.teamdetail.domain.GetTeamDetailUseCase

internal class TeamDetailViewModel(
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel()
