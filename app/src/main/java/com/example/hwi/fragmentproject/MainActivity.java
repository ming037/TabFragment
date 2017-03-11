package com.example.hwi.fragmentproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onListRecipeSelected(int index) {
        //여기에 쓰는 것이 List_Fragment에서 일어나는 일이다.
        //UI의 변화는 Activity가 관리하기 때문에 이렇게 하는것.
        Toast.makeText(this, Recipes.names[index], Toast.LENGTH_SHORT).show();
    }
}

/*
10. fragmentManager 변수를 생성한다. (support가 아닌 app임을 생각),
    얘는 계속해서 우리가 fragment를 살펴 볼 수 있도록 하고 back stack of fragment를 관리하고
    fragment transactions API에 접근 할 수 있게 한다. 이 말은 fragment를 추가하고 삭제하는 것을
    가능하게 한다는 뜻

11. fragmentTransaction 변수를 만들고 fragmentManager의 transaction을 시작한다.
    주의할 점은 Transaction은 반드시 commit을 해줘야 한다는 것이다. fragmentTransaction.commit(); 추가
    디비처럼 commit하기 전까지는 변화가 적용되지 않는다.

12. fragmentTransaction에 add를 통해서 추가해주자. 첫번째 인자는 놓여야할 곳 즉,activity_main에서의 frameLayout을 넣고
    두 번째는 넣어야할 fragment인 listfragment를 넣자.

13. 여기서 그냥 실행을 해보면 에러를 얻는데 이는 fragment자체의 lifecycle과 activity의 lifecycle이 충돌하기 때문에 그렇다.
    이걸 해결하기 위해서 Listfragment의 inflate함수의 세 번째 인자로 false를 넣어주자.
    세 번째 인자는 해당 view를 두 번째 인자인 view group에 attach할지 안할지를 선택하는 것인데, onCreateView가 view를 return할 때,
    시스템에 의해서 자동으로 view group에 추가 되기 때문에 false를 해주지 않으면 두 번 연속으로 attach되서 에러를 일으키는 것이다.
    여기까지 하면 fragment 삽입 성공이다.
 */