package com.mona_daigle.diet_meal_plan.models

import com.mona_daigle.diet_meal_plan.R
import java.io.Serializable

data class Meal(
    var imageMeal: Int,
    var titleMeal: String,
    var priceMeal: String,
    var desMeal: String,
    var isLikeMeal: Int = 0,
) : Serializable

val dataMeals = listOf<Meal>(
//    Meal(
//        R.drawable.a1,
//        "About the Mediterranean Diet",
//        "",
//        "What makes the Mediterranean diet so great is that it’s a lifestyle, not a traditional weight-loss plan. This meal plan won't have you counting calories or measuring portions (snooze), nor should any Mediterranean diet you attempt. You’ll fill up on tons of veggies, fruit, 100% whole grains, and beans; choose lean protein like seafood and eggs; and enjoy sweets and alcohol as indulgences. The benefit of eating this way is that it emphasizes real, whole foods and limits ultra-processed ones, which tend to be higher in sodium, saturated fat, and added sugar. Since the Mediterranean eating style prioritizes the fun and enjoyment of your whole dining experience, flavorful ingredients are at the forefront — so you’ll never feel deprived. While this diet is renowned by nearly all leading experts, it's always best to talk about the pros and cons of a longterm diet with your doc — be sure to bring this up at your next check in if you decide to stick with it.",
//    ),
    Meal(
        R.drawable.a2,
        "Monday: Breakfast",
        "",
        "Start the day with this flavorful and vibrant Shakshuka recipe, known as a staple breakfast dish throughout the Middle East. Serve with two slices of sprouted grain bread.",
    ),
    Meal(
        R.drawable.a3,
        "Monday: Lunch",
        "",
        "In a bowl, combine 7 1/2 ounces (half a 15-ounce can) canned chickpeas (rinse in a colander for two minutes to remove excess sodium and drain well; save other half for Tuesday's snack), 2 teaspoons olive oil, 1/4 cup chopped white onion, 1/4 cup chopped green pepper (save the rest of the onion and pepper for dinner), 1 tablespoon sliced black olives, 1/4 teaspoon ground black pepper, and 1 1/2 tablespoons white vinegar. Mix thoroughly. Serve mixture over 2 cups romaine lettuce leaves. ",
    ),
    Meal(
        R.drawable.a4,
        "Monday: Snack",
        "",
        "Spread one slice of 100% whole-grain bread or 2 whole-grain flatbread crackers with 2 tablespoons hummus, drizzle with 1 teaspoon of olive oil, and add salt, pepper, or other seasonings to taste.",
    ),
    Meal(
        R.drawable.a5,
        "Monday: Dinner",
        "",
        "Slice remainder of white onion and green pepper from lunch into chunks; set out 10 grape tomatoes. Alternate pieces of onion, pepper, and cherry tomatoes on skewers and grill. Serve with 5 ounces of grilled salmon and one 6-inch whole-wheat pita pocket. Spread pita with 2 tablespoons hummus. Finish with 1 cup fat-free milk.",
    ),
    Meal(
        R.drawable.a6,
        "Tuesday: Breakfast",
        "",
        "In a clear, wide-mouth glass, layer 1/2 cup 2% Greek yogurt with 1 cup raspberries and 1/3 cup lower sugar granola, like KIND Peanut Butter Clusters.",
    ),
    Meal(
        R.drawable.a7,
        "Tuesday: Lunch",
        "",
        "Eat one Freshé pack (the canned tuna salad comes in flavorful varieties like Provence Nicoise and Sicilian Caponata) with a piece of fruit.",
    ),
    Meal(
        R.drawable.a8,
        "Tuesday: Snack",
        "",
        "Make this hummus in advance and have half the recipe today, and save the rest for Wednesday's snack (or top it with an egg for breakfast fun!). Use remaining chickpeas from Monday's lunch (half a 15-ounce can). Mash the chickpeas lightly in a bowl with a fork. Mix in 2 teaspoons olive oil, 1 clove minced garlic, 1 tablespoon lemon juice, and 1/4 teaspoon salt. If desired, add 1/4 teaspoon ground cumin. Mash all ingredients together thoroughly or, if a smoother spread is desired, use a food processor to blend the ingredients. Bring along 1 cup broccoli florets and 1 sliced pepper for dipping.",
    ),
    Meal(
        R.drawable.a9,
        "Tuesday: Dinner",
        "",
        "Slice a 6-inch French baguette roll in half lengthwise. Sprinkle the halves with 1/4 cup shredded mozzarella cheese and bake in toaster oven at 250 degrees for four to six minutes, until cheese is just beginning to melt. Meanwhile, slice two large red tomatoes. Remove baguette from toaster oven, sprinkle with a little dried basil and dried oregano if desired. Top with tomato slices. For dessert, enjoy 1 ounce of dark chocolate.",
    ),
    Meal(
        R.drawable.a10,
        "Wednesday: Breakfast",
        "",
        "Today, make half of this Chive and Goat Cheese Frittata recipe. Serve half of the frittata now, and refrigerate the rest for Thursday dinner. Enjoy with an 8-ounce latte with skim or unsweetened soy milk.",
    ),
    Meal(
        R.drawable.a11,
        "Wednesday: Lunch",
        "",
        "Spread two slices of whole-wheat bread with 1/2 avocado and stuff with 3 ounces sliced turkey breast, 5 artichoke hearts, and as much sliced red pepper as you want. Serve with a cup each of baby carrots and grapes.",
    ),
    Meal(
        R.drawable.a12,
        "Wednesday: Snack",
        "",
        "Use remaining chickpea spread from Tuesday's snack. Bring along sliced crudité plus a 1-ounce piece of cheese.",
    ),
    Meal(
        R.drawable.a13,
        "Wednesday: Dinner",
        "",
        "Today, make half of this Mediterranean Grilled Sea Bass recipe and reserve half of that for Thursday lunch. Increase your vegetable intake by serving half a bag of baby arugula leaves with this meal (save the other half for Thursday). Add one ear of corn and 1 cup cooked sugar snap peas topped with 2 teaspoons butter on the side. For dessert, have one frozen fruit popsicle (about 80 calories or less).",
    ),
    Meal(
        R.drawable.a14,
        "Thursday: Breakfast",
        "",
        "Make 1 cup of oatmeal (like Good Housekeeping Nutritionist Approved McCann's Irish Oatmeal) and add ½ cup milk and hot water as desired. Serve with 1 cup fresh berries and sprinkle of cinnamon. Add a drizzle of raw honey for sweetness if needed.",
    ),
    Meal(
        R.drawable.a15,
        "Thursday: Lunch",
        "",
        "Serve remaining sea bass from Wednesday's dinner over rest of the baby arugula leaves.",
    ),
    Meal(
        R.drawable.a16,
        "Thursday: Snack",
        "",
        "Mix 1/2 cup 0% plain, unsweetened Greek yogurt with 1 tablespoon light maple syrup and 1/4 teaspoon vanilla extract. Dip fresh crudité in this sweet, creamy dip.",
    ),
    Meal(
        R.drawable.a17,
        "Thursday: Dinner",
        "",
        "Have the rest of the frittata from Wednesday's breakfast. Serve with 2 cups baby spinach leaves, topped with 2 tablespoons balsamic vinegar, and 1 cup fat-free milk. Have one slice of whole-wheat toast topped with 2 teaspoons butter. For dessert, have a single-serve ice cream, like a Magnum Mini Bar.",
    ),
    Meal(
        R.drawable.a18,
        "Friday: Breakfast",
        "",
        "Top 2 slices of 100% whole-grain bread with 2 tablespoons of nut butter and 1/2 sliced banana. Sprinkle on a little cinnamon for extra flavor.",
    ),
    Meal(
        R.drawable.a19,
        "Friday: Lunch",
        "",
        "Mix together 1/2 cup 2% Greek yogurt with 1/2 finely chopped cucumber, 1/2 minced garlic clove, and a shake of salt and pepper if desired. Spread half of yogurt sauce (save remaining sauce for later use) on a 100% whole-grain sandwich thin (like Arnold's) or pita and eat with a cup or more of veggies.",
    ),
    Meal(
        R.drawable.a20,
        "Friday: Snack",
        "",
        "Munch on a 1.5-ounce bag of roasted chickpeas, like Biena Honey-Roasted Chickpea Snacks.",
    ),
    Meal(
        R.drawable.a21,
        "Friday: Dinner",
        "",
        "Make one fourth of this Mediterranean Sweet and Sour Chicken recipe. Serve with 1/2 cup cooked brown rice topped with 2 teaspoons butter. Enjoy with a glass of wine.",
    ),
    Meal(
        R.drawable.a22,
        "Saturday: Breakfast",
        "",
        "Whip up 2 eggs any style with 1/3 sliced avocado and 1 piece 100% whole-grain bread.",
    ),
    Meal(
        R.drawable.a23,
        "Saturday: Lunch",
        "",
        "Top one half of a Good Housekeeping Nutritionist Approved Green Giant Cauliflower Pizza Crust with tons of veggies (leftovers or whatever you feel like) and 1/2 cup mix of part-skim shredded cheese. Eat with a green salad (at least 2 cups) topped with 2 tablespoons regular dressing, any variety. For dessert, have one scoop of your favorite ice cream in a plain cone.",
    ),
    Meal(
        R.drawable.a24,
        "Saturday: Snack",
        "",
        "Combine 1/2 fresh orange and 1/2 cup pineapple chunks (fresh or canned and drained) with 6 ounces Greek yogurt for a fruity smoothie. Blend in a food processor or blender, dropping in ice cubes as desired.",
    ),
    Meal(
        R.drawable.a25,
        "Saturday: Dinner",
        "",
        "At your favorite Greek restaurant, order sautéed or grilled shrimp or salmon with an extra order of veggies. Split an app with your table and skip the bread basket. Enjoy with a glass of wine or spirit on the rocks.",
    ),
    Meal(
        R.drawable.a26,
        "Sunday: Breakfast",
        "",
        "Top a 100% whole-grain bagel thin with 1/3 cup fat-free ricotta cheese mixed with 1 tablespoon each peanut butter and honey. Sprinkle 1 tablespoon raisins on top.",
    ),
    Meal(
        R.drawable.a27,
        "Sunday: Lunch",
        "",
        "Make this wild rice and egg bowl. Grab a skillet and cook one egg, then add 2 cups spinach and season with salt and pepper. Serve on top of Minute Rice Multi-Grain Medley.",
    ),
    Meal(
        R.drawable.a28,
        "Sunday: Snack",
        "",
        "Nosh on 1 ounce nuts, like GH Nutritionist-approved favorites Hampton Farms peanuts or Wonderful Pistachios.",
    ),
    Meal(
        R.drawable.a29,
        "Sunday: Dinner",
        "",
        "Marinate 6 ounces of shrimp in a basil marinade for at least 30 minutes or overnight. To make basil marinade: Whisk together 1/4 cup white wine vinegar, 1 teaspoon olive oil, 1 tablespoon lemon juice, and 1/8 cup chopped fresh basil or 1 teaspoon dried basil. Grill shrimp until cooked through. Top 2 cups of romaine lettuce with the shrimp and mix well into greens for added flavor. Serve with 1 cup blueberries and enjoy 1 ounce chocolate (about 4 Dove Miniatures) for dessert.",
    ),
)

