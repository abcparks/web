import cn.alex.dao.BaseDao;
import org.junit.Test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by WCY on 2021/7/30
 */
public class SPITest {
    @Test
    public void serviceLoader() {
        ServiceLoader<BaseDao> serviceLoader = ServiceLoader.load(BaseDao.class);
        Iterator<BaseDao> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            BaseDao baseDao = iterator.next();
            System.out.println(baseDao);
        }

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //calendar.set(Calendar.MONTH, 3);
        //calendar.set(Calendar.DAY_OF_MONTH, 1);
        //calendar.add(Calendar.DAY_OF_YEAR, -1);
        //System.out.println(calendar.getTime());


    }
}
