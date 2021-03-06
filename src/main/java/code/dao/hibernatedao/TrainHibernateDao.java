package code.dao.hibernatedao;

import code.model.Train;
import code.dao.daointerface.ITrainDao;
import code.model.Race;
import code.model.TrainType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class TrainHibernateDao extends GenericHibernateDao<Train, Integer> implements ITrainDao {
    public TrainHibernateDao() {
        super(Train.class);
    }

    public List<Train> getTrainsWithType(TrainType trainType) {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(Train.class, "train");
        criteria.add(Restrictions.eq("train.trainType", trainType));
        return criteria.list();
    }

    public Collection<Race> getRacesForTrain(Train train) {
        return train.getRaces();
    }
    public Train getTrainByID(Integer id) {
        return super.findByPK(id);
    }
    public Integer getCarriageAmountForTrain(Train train) {
        return train.getCarriageAmount();
    }
    public TrainType getTypeForTrain(Train train) {
        return train.getTrainType();
    }
}
