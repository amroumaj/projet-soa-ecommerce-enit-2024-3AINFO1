using System.ComponentModel.DataAnnotations;

namespace ReviewService.Models
{
    public class Review
    {
        public int Id { get; set; }
        public int ProduitId { get; set; }
        public int UserId { get; set; }
        [Required]
        [StringLength(500, ErrorMessage = "Le commentaire ne peut pas dépasser 500 caractères.")]
        public required string Commentaire { get; set; }
        public DateTime dateCreation { get; set; } = DateTime.UtcNow;
    }
}
