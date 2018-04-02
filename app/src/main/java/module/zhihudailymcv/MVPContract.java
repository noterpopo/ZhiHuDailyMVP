package module.zhihudailymcv;

/**
 * Created by lgp on 2018/3/27.
 */

public interface MVPContract {
    interface Model{
        void initiate(Presenter presenter);
    }
    interface View{
        void initiate(Presenter presenter);
    }
    interface Presenter{
        void initiate(Model model,View view);
    }
}