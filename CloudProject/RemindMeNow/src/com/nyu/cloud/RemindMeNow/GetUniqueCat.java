package com.nyu.cloud.RemindMeNow;

//import googleapi_places.arr_cat_class;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetUniqueCat {
	public ArrayList<String> cat_to_search(){
		ArrayList<Arr_cat_desc_class> arr_cat_desc = new ArrayList<Arr_cat_desc_class>();
		ArrayList<String> arr_cat = new ArrayList<String>();
		
		// -------------change the query---------------//
		//String sql = "SELECT Description, Category FROM //table_name// WHERE username ='"+//user_id from front end//+"' AND flag = '0'"; 
		//ResultSet rs = statement.executeQuery(sql);
		//while(rs.next())
		//{
			//arr_cat_class arr_cat_obj = new arr_cat_class();
			//arr_cat_obj.set_cat(rs.getString("Description"));
			//arr_cat_obj.set_desc(rs.getString("Category"));
			//arr_cat_desc.add(arr_cat_obj);					
		//}
		// ------------- Replacement -------------------//
		/*
		Replace this part with above snippet 
		*/
		Arr_cat_desc_class arr_cat_obj1 = new Arr_cat_desc_class();
		arr_cat_obj1.set_cat("food");
		arr_cat_obj1.set_desc("Rice");
		
		Arr_cat_desc_class arr_cat_obj2 = new Arr_cat_desc_class();
		arr_cat_obj2.set_cat("clothing_store");
		arr_cat_obj2.set_desc("Shirt");
		
		Arr_cat_desc_class arr_cat_obj3 = new Arr_cat_desc_class();
		arr_cat_obj3.set_cat("food");
		arr_cat_obj3.set_desc("Milk");
		
		Arr_cat_desc_class arr_cat_obj4 = new Arr_cat_desc_class();
		arr_cat_obj4.set_cat("food");
		arr_cat_obj4.set_desc("Bread");
		
		Arr_cat_desc_class arr_cat_obj5 = new Arr_cat_desc_class();
		arr_cat_obj5.set_cat("jewelry_store");
		arr_cat_obj5.set_desc("Earings");
		
		Arr_cat_desc_class arr_cat_obj6 = new Arr_cat_desc_class();
		arr_cat_obj6.set_cat("hardware_store");
		arr_cat_obj6.set_desc("Lights");
				
		arr_cat_desc.add(arr_cat_obj1);
		arr_cat_desc.add(arr_cat_obj2);
		arr_cat_desc.add(arr_cat_obj3);
		arr_cat_desc.add(arr_cat_obj5);
		arr_cat_desc.add(arr_cat_obj6);
		arr_cat_desc.add(arr_cat_obj4);
		
		/*
		Replace this part with above snippet 
		*/
		
		for (int i=0; i<arr_cat_desc.size(); i++){
			boolean flag = false;
			String category = arr_cat_desc.get(i).get_cat();
			String description = arr_cat_desc.get(i).get_desc();
			
			System.out.print(category + " ");
			System.out.println(description);
					
			if (arr_cat.size() == 0){
				arr_cat.add(category);
			} else {
				for (int k=0; k<arr_cat.size(); k++){
					if (arr_cat.get(k).equals(category)){
						flag = true;
						break;
					}
				}
				
				if (!flag){
					arr_cat.add(category);
				}
			}
			
		}
		
		System.out.println("***************************************");
		System.out.println("Unique categories: ");
		System.out.println("***************************************");
		/*
		for (int s=0; s<arr_cat.size(); s++){
			System.out.println (arr_cat.get(s));
		}
		*/
		return arr_cat;
		
	}
}
