class OrganTransplantMatchingSystem:
    def __init__(self, compatibility_matrix):
        """
        our constructor accepts the compatibility_matrix a matrix of size Number_of_donors * Numbers_of_recipients
        which is calculated considering different cases like health status, blood type, age.... 

          GUIDE:
            compatibility_matrix[i][j] represents  compatibility Score between Donor i and Recipient j.
            
        we will store all the matches in self.matches array;
        """
        self.compatibility_matrix = compatibility_matrix
        self.matches = [] 

    def find_best_match(self, donor_id, matched_recipients):
        #this method accepts the current donor_id and the matched_recipients array, 
        # so that we won't give the organ to one receiver twice
        #   main greedy matcher algorithm, each iteration we check the best score and set it,

        best_recipient = None
        best_score = -1
        for recipient_id, score in enumerate(self.compatibility_matrix[donor_id]):
            if recipient_id not in matched_recipients and score > best_score:
                best_recipient = recipient_id
                best_score = score

        return best_recipient, best_score

    def match(self):
        # we always check if the recipient is already matched or not, the matched_recipients is used for that 
        # purpose in the find_best_match method 

        received_recipients = set()
        for donor_id in range(len(self.compatibility_matrix)):
            recipient_id, score = self.find_best_match(donor_id, received_recipients)
            if recipient_id is not None:
                self.matches.append((donor_id, recipient_id, score))
                received_recipients.add(recipient_id)


        print("Final match between donors and recipients is: ")
        for donor, recipient, score in self.matches:
            print(f"Donor_id: {donor} -> Recipient_id:{recipient} --->( compatibility_score : {score} )")
  



# Rows = Donors, Columns = Recipients and row_id = donor_id , index of the element an array = recipient id

compatibility_matrix = [
    [90, 70, 50], 
    [80, 60, 70], 
    [60, 80, 40],  
]

matching_system = OrganTransplantMatchingSystem(compatibility_matrix)
matching_system.match()
