[1mdiff --git a/Meme/src/main/java/com/memetube/controller/ApiController.java b/Meme/src/main/java/com/memetube/controller/ApiController.java[m
[1mindex 8266929..b22acb9 100644[m
[1m--- a/Meme/src/main/java/com/memetube/controller/ApiController.java[m
[1m+++ b/Meme/src/main/java/com/memetube/controller/ApiController.java[m
[36m@@ -5,6 +5,7 @@[m [mimport java.util.List;[m
 import javax.servlet.ServletException;[m
 import javax.servlet.http.HttpServletRequest;[m
 [m
[32m+[m[32mimport org.junit.experimental.categories.Categories;[m
 import org.springframework.beans.factory.annotation.Autowired;[m
 import org.springframework.http.HttpStatus;[m
 import org.springframework.http.ResponseEntity;[m
[36m@@ -15,7 +16,8 @@[m [mimport org.springframework.web.bind.annotation.RequestMethod;[m
 import org.springframework.web.bind.annotation.RequestParam;[m
 import org.springframework.web.bind.annotation.RestController;[m
 [m
[31m-import com.memetube.models.Meme;[m
[32m+[m[32mimport com.memetube.models.Category;[m
[32m+[m[32mimport com.memetube.models.VoteMeme;[m
 import com.memetube.service.MemeService;[m
 [m
 import io.jsonwebtoken.Claims;[m
[36m@@ -25,7 +27,6 @@[m [mimport io.jsonwebtoken.Claims;[m
 public class ApiController {[m
 	@Autowired[m
 	public MemeService ms;[m
[31m-	[m
 [m
 	@SuppressWarnings("unchecked")[m
 	@RequestMapping(value = "role/{role}", method = RequestMethod.GET)[m
[36m@@ -35,35 +36,53 @@[m [mpublic class ApiController {[m
 		return ((List<String>) claims.get("roles")).contains(role);[m
 	}[m
 [m
[31m-	[m
 	@RequestMapping(value = "/memes", method = RequestMethod.POST)[m
[31m-	public ResponseEntity<Meme> addMeme(@RequestBody Meme memeForAdd) {[m
[31m-		//ms.addMeme[m
[31m-		return new ResponseEntity<Meme>(HttpStatus.OK);[m
[32m+[m	[32mpublic ResponseEntity<Integer> addVoteMeme(@RequestBody VoteMeme memeForAdd, final HttpServletRequest request) {[m
[32m+[m[41m		[m
[32m+[m[41m		[m
[32m+[m		[32mfinal Claims claims = (Claims) request.getAttribute("claims");[m
[32m+[m		[32mInteger userId =  (Integer) claims.get("id");[m
[32m+[m[41m		[m
[32m+[m		[32mint memeId = ms.addMeme(memeForAdd.getTitle(), memeForAdd.getImage(), memeForAdd.getCategoryId(), userId);[m
[32m+[m[41m		[m
[32m+[m		[32m// ms.addVoteMeme[m
[32m+[m		[32mreturn new ResponseEntity<Integer>(memeId, HttpStatus.OK);[m
 	}[m
[31m-	[m
[32m+[m
 	@RequestMapping(value = "/memes/{id}/upvote", method = RequestMethod.POST)[m
[31m-	public ResponseEntity<Meme> upVoteMeme(@PathVariable("id") int id) {[m
[31m-		//ms.upVoteMeme[m
[31m-		return new ResponseEntity<Meme>(HttpStatus.OK);[m
[32m+[m	[32mpublic ResponseEntity<VoteMeme> upVoteMeme(@PathVariable("id") int id, final HttpServletRequest request) {[m
[32m+[m		[32m// ms.upVoteVoteMeme[m
[32m+[m[41m		[m
[32m+[m		[32mfinal Claims claims = (Claims) request.getAttribute("claims");[m
[32m+[m		[32mInteger userId =  (Integer) claims.get("id");[m
[32m+[m[41m		[m
[32m+[m[41m		[m
[32m+[m		[32mreturn new ResponseEntity<VoteMeme>(HttpStatus.OK);[m
 	}[m
[31m-	[m
[32m+[m
 	@RequestMapping(value = "/memes/{id}/downvote", method = RequestMethod.POST)[m
[31m-	public ResponseEntity<Meme> downVoteMeme(@PathVariable("id") int id) {[m
[31m-		//ms.downVoteMeme[m
[31m-		return new ResponseEntity<Meme>(HttpStatus.OK);[m
[32m+[m	[32mpublic ResponseEntity<Integer> downVoteMeme(@PathVariable("id") int id, final HttpServletRequest request) {[m
[32m+[m[41m		[m
[32m+[m		[32mfinal Claims claims = (Claims) request.getAttribute("claims");[m
[32m+[m		[32mInteger userId =  (Integer) claims.get("id");[m
[32m+[m[41m		[m
[32m+[m		[32m// ms.downVoteVoteMeme[m
[32m+[m		[32mreturn new ResponseEntity<Integer>(id, HttpStatus.OK);[m
 	}[m
[32m+[m
 	@RequestMapping(value = "/categories", method = RequestMethod.GET)[m
[31m-	public ResponseEntity<Meme> getCategories() {[m
[31m-		//ms.getCategories[m
[31m-		return new ResponseEntity<Meme>(HttpStatus.OK);[m
[32m+[m	[32mpublic ResponseEntity<List<Category>> getCategories() {[m
[32m+[m		[32mList<Category> listOfAllCategories = ms.getAllCategories();[m
[32m+[m		[32m// ms.getCategories[m
[32m+[m		[32mreturn new ResponseEntity<List<Category>>(listOfAllCategories, HttpStatus.OK);[m
 	}[m
[31m-	[m
[31m-	[m
[31m-	@RequestMapping(value = "/meme", method = RequestMethod.GET)[m
[31m-	public ResponseEntity<Meme> get(@RequestParam("category_id") int categoryId, @RequestParam("page") int page, @RequestParam("page_size") int pageSize) {[m
[31m-		//ms.getFrankenstainMeme ? [m
[31m-		return new ResponseEntity<Meme>(HttpStatus.OK);[m
[32m+[m
[32m+[m	[32m@RequestMapping(value = "/memes", method = RequestMethod.GET)[m
[32m+[m	[32mpublic ResponseEntity<List<VoteMeme>> get(@RequestParam(value = "category_id", required = false) int categoryId,[m
[32m+[m			[32m@RequestParam("page") int page, @RequestParam("page_size") int pageSize) {[m
[32m+[m		[32m// ms.getFrankenstainVoteMeme ?[m
[32m+[m		[32mList<VoteMeme> listOfMemes = ms.getMemesForCategory(categoryId, page, pageSize);[m
[32m+[m		[32mreturn new ResponseEntity<List<VoteMeme>>(listOfMemes, HttpStatus.OK);[m
 	}[m
 [m
 }[m
