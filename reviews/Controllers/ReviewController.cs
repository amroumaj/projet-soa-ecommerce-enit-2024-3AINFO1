using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ReviewService.Data;

namespace ReviewService.Controllers
{
    [ApiController]
    [Route("api/reviews")]
    public class ReviewController : Controller
    {
        private readonly ReviewContext _context;

        public ReviewController(ReviewContext context)
        {
            _context = context;
        }

        [HttpPost]
        public async Task<IActionResult> AddReview([FromBody] ReviewService.Models.Review review)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            _context.Reviews.Add(review);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetReviewsByProduct), new { idProduit = review.ProduitId }, review);
        }


        [HttpGet("{idProduit}")]
        public async Task<ActionResult<IEnumerable<ReviewService.Models.Review>>> GetReviewsByProduct(int idProduit)
        {
            var reviews = await _context.Reviews
                .Where(r => r.ProduitId == idProduit)
                .ToListAsync();

            if (!reviews.Any())
            {
                return NotFound("Aucun avis trouvé pour ce produit.");
            }

            return Ok(reviews);
        }
    }
}
