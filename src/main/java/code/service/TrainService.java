package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.ITrainDao;
import code.model.Train;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class TrainService extends GenericService<Train, Integer> {
    private ITrainDao dao;

    public TrainService() {
        super(Train.class);
    }

    @Override
    public ITrainDao getDao() {
        if (dao == null) {
            dao = (ITrainDao) AbstractDaoFactory.getImplDao(Train.class);
        }

        return dao;
    }
}

