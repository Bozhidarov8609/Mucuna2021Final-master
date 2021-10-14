package ex.service;

import ex.model.service.DealerServiceModel;

public interface DealerService {

    DealerServiceModel registerDealer(DealerServiceModel dealerServiceModel);

  DealerServiceModel findDealerByCompanyName(String dealerName);
}
