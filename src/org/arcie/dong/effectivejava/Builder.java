package org.arcie.dong.effectivejava;
/**
 * @author 刘春东
 * @version 
 * @time 2014年2月1日上午1:43:13
 */
public class Builder {
	//构建器
}
///////////////////////////////////////////////////////////////////////////
// Telescoping constructor pattern - does not scale well!
class NutritionFacts {
	private final int servingSize; // (mL)  required
	private final int servings; // (per container) required
	private final int calories; //  optional
	private final int fat; // (g) optional
	private final int sodium; // (mg) optional
	private final int carbohydrate; // (g)  optional
	
	public NutritionFacts(int servingSize, int servings) {
		this(servingSize, servings, 0);
	}
	public NutritionFacts(int servingSize, int servings,	int calories) {
		this(servingSize, servings, calories, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories, int fat) {
		this(servingSize, servings, calories, fat, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
		this(servingSize, servings, calories, fat, sodium, 0);
	}
	public NutritionFacts(int servingSize, int servings,
		int calories, int fat, int sodium, int carbohydrate) {
		this.servingSize = servingSize;
		this.servings = servings;
		this.calories = calories;
		this.fat = fat;
		this.sodium = sodium;
		this.carbohydrate = carbohydrate;
	}
}
//////////////////////////////////////////////////////////////////////////
//JavaBeans Pattern - allows inconsistency, mandates mutability
class NutritionFacts1 {
	// Parameters initialized to default values (if any)
	private int servingSize = -1; // Required; no default value
	private int servings = -1; // " " " "
	private int calories = 0;
	private int fat = 0;
	private int sodium = 0;
	private int carbohydrate = 0;
	
	//Default Constructor
	public NutritionFacts1() { 	}

	// Setters
	public void setServingSize(int val) {
		servingSize = val;
	}

	public void setServings(int val) {
		servings = val;
	}

	public void setCalories(int val) {
		calories = val;
	}

	public void setFat(int val) {
		fat = val;
	}

	public void setSodium(int val) {
		sodium = val;
	}

	public void setCarbohydrate(int val) {
		carbohydrate = val;
	}
}
//////////////////////////////////////////////////////////////////////////
//Builder Pattern
class NutritionFacts2 {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	//Static Class
	public static class Builder {
		// Required parameters
		private final int servingSize;
		private final int servings;
		// Optional parameters - initialized to default values
		private int calories = 0;
		private int fat = 0;
		private int carbohydrate = 0;
		private int sodium = 0;

		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		//like setter
		public Builder calories(int val) {
			calories = val;
			return this;
		}

		public Builder fat(int val) {
			fat = val;
			return this;
		}

		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		public Builder sodium(int val) {
			sodium = val;
			return this;
		}

		public NutritionFacts2 build() {
			return new NutritionFacts2(this);
		}
	}

	private NutritionFacts2(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}
}