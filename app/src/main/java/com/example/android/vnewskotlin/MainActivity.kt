package com.example.android.vnewskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vnewskotlin.adapter.CategoryAdapter
import com.example.android.vnewskotlin.adapter.NewsAdapter
import com.example.android.vnewskotlin.api.RetrofitInstance
import com.example.android.vnewskotlin.model.ArticlesModel
import com.example.android.vnewskotlin.model.CategoryModel
import com.example.android.vnewskotlin.model.NewsModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CategoryAdapter.CategoryClickInterface  {

    lateinit var newsAdapter : NewsAdapter
    lateinit var categoryAdapter: CategoryAdapter

    private val categoryModelList  = ArrayList<CategoryModel>()
    private val articlesNewList = ArrayList<ArticlesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // setting adapter to news recycler view
        newsAdapter = NewsAdapter(this@MainActivity,articlesNewList)
        newsRes.layoutManager = LinearLayoutManager(this@MainActivity)
        newsRes.adapter = newsAdapter


        // setting adapter to category recycler view
        categoryAdapter = CategoryAdapter(this@MainActivity,categoryModelList, this)
        categoryRes.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        categoryRes.adapter = categoryAdapter

        getNews("All")
        getCaterories()

    }



    // setting categories
    private fun getCaterories() {
        categoryModelList.add(CategoryModel("All","https://media.istockphoto.com/photos/map-of-india-on-digital-display-picture-id1368021960?k=20&m=1368021960&s=612x612&w=0&h=StzAhrkFJJK8_wA4-_natdu08rGu-mxvso9H7Sx3v_w="))
        categoryModelList.add(CategoryModel("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        categoryModelList.add(CategoryModel("Science","https://images.unsplash.com/photo-1649290273851-d4536b14da7b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxjb2xsZWN0aW9uLXBhZ2V8MXwxMTc4OTEyfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        categoryModelList.add(CategoryModel("Sports","https://images.unsplash.com/photo-1612872087720-bb876e2e67d1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHNwb3J0c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        categoryModelList.add(CategoryModel("General","https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGdlbmVyYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"))
        categoryModelList.add(CategoryModel("Business","https://images.unsplash.com/photo-1584090818302-5203b72aca5f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8YnVpc2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        categoryModelList.add(CategoryModel("Entertainment","https://images.unsplash.com/photo-1514525253161-7a46d19cd819?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        categoryModelList.add(CategoryModel("Health","https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        categoryAdapter.notifyDataSetChanged();

    }



    // making connection with api
    private fun getNews(category : String) {
        progressBar.visibility = View.VISIBLE

        var newsList : Call<NewsModel>
        val allNewsUrl : String = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=b2e63ee8bc1e4c2eba94722abf12e091"
        val categoryUrl : String =  "https://newsapi.org/v2/top-headlines?country=in&category=$category&apiKey=b2e63ee8bc1e4c2eba94722abf12e091"

        // so if category is All then allNewsUrl will be called and if other than that then categoryUrl will be called
        newsList = if (category == "All") {
            RetrofitInstance.retrofitInstance.getAllNews(allNewsUrl)
        } else {
            RetrofitInstance.retrofitInstance.getNewsByCategory(categoryUrl)
        }

        // fetching all the data coming from api and storing it in articlesNewsList
        newsList.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                progressBar.visibility = View.GONE
                articlesNewList.clear()
                val news = response.body()
                if (news != null) {
                    val allArticlesList : List<ArticlesModel> =  news.articles

                    for (i in allArticlesList) {
                        articlesNewList.add(ArticlesModel(i.title,i.description,i.url,i.urlToImage,i.content))

                    }

                    newsAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("Vinayak","Failed to get data")
            }
        })
    }

    // overriding CategoryClickInterface interface method and getting particular category and giving it to getNews() method
    override fun onCategoryClick(position: Int) {
        val category : String = categoryModelList[position].category
        getNews(category)
    }

}