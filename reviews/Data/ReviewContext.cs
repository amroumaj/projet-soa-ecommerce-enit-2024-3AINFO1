using Microsoft.EntityFrameworkCore;

namespace ReviewService.Data
{ 
    public class ReviewContext : DbContext
    {
        public ReviewContext(DbContextOptions<ReviewContext> options) : base(options) { }
        public DbSet<ReviewService.Models.Review> Reviews { get; set; }
    }
}
