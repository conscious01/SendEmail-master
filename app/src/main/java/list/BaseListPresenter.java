package list;

/**
 * MVP列表类Presenter基类
 * Created by wison on 2017/3/23.
 */
public interface BaseListPresenter {

    void loadData();

    void loadMore(int page);

}
