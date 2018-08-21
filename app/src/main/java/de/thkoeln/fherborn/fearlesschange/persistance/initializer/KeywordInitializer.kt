package de.thkoeln.fherborn.fearlesschange.persistance.initializer

import de.thkoeln.fherborn.fearlesschange.persistance.models.Keyword

class KeywordInitializer : DataInitializer<Keyword>("keyword") {

    override fun getItemValues(item: Keyword?) = hashMapOf<String, Any?>(
            "keyword" to item?.keyword
    )

    override fun getItems() = listOf(
            Keyword(1, "fear"),
            Keyword(2, "scare"),
            Keyword(3, "positive"),
            Keyword(4, "motivate"),
            Keyword(5, "hope"),
            Keyword(6, "influence"),
            Keyword(7, "step-by-step"),
            Keyword(8, "Action-Plan"),
            Keyword(9, "milestone"),
            Keyword(10, "goal"),
            Keyword(11, "what"),
            Keyword(12, "where"),
            Keyword(13, "when"),
            Keyword(14, "control"),
            Keyword(15, "easier"),
            Keyword(16, "path"),
            Keyword(17, "route"),
            Keyword(18, "encourage"),
            Keyword(19, "obstacles"),
            Keyword(20, "block"),
            Keyword(21, "barrier"),
            Keyword(22, "introduce"),
            Keyword(23, "key message"),
            Keyword(24, "minto"),
            Keyword(25, "key statement"),
            Keyword(26, "presentation"),
            Keyword(27, "core statement"),
            Keyword(28, "small talk"),
            Keyword(29, "emotional"),
            Keyword(30, "feelings"),
            Keyword(31, "persuading"),
            Keyword(32, "human touch"),
            Keyword(33, "vision"),
            Keyword(34, "refine"),
            Keyword(35, "reevaluate"),
            Keyword(36, "iterative approach"),
            Keyword(37, "baby steps"),
            Keyword(38, "reflection"),
            Keyword(39, "future"),
            Keyword(40, "help"),
            Keyword(41, "assistance"),
            Keyword(42, "time management"),
            Keyword(43, "person"),
            Keyword(44, "experts"),
            Keyword(47, "resources"),
            Keyword(48, "skillful"),
            Keyword(49, "professional"),
            Keyword(50, "experienced"),
            Keyword(51, "assignment"),
            Keyword(52, "imagine"),
            Keyword(54, "possibility"),
            Keyword(55, "convince"),
            Keyword(56, "outcome"),
            Keyword(57, "what if"),
            Keyword(58, "ask"),
            Keyword(59, "know"),
            Keyword(60, "yourself"),
            Keyword(61, "me"),
            Keyword(62, "i"),
            Keyword(63, "passion"),
            Keyword(64, "talents"),
            Keyword(65, "abilities"),
            Keyword(66, "own"),
            Keyword(67, "self"),
            Keyword(68, "strengths"),
            Keyword(69, "weakness"),
            Keyword(70, "likes"),
            Keyword(71, "dislikes"),
            Keyword(72, "principles"),
            Keyword(73, "Low hanging"),
            Keyword(74, "quick"),
            Keyword(75, "easy"),
            Keyword(76, "low risk"),
            Keyword(77, "win"),
            Keyword(78, "visible impact"),
            Keyword(79, "myth"),
            Keyword(80, "misconception"),
            Keyword(81, "incorrect"),
            Keyword(82, "assumption"),
            Keyword(83, "impression"),
            Keyword(84, "false"),
            Keyword(85, "reality"),
            Keyword(86, "battle"),
            Keyword(87, "resistance"),
            Keyword(88, "fight"),
            Keyword(90, "deep breath"),
            Keyword(91, "worth"),
            Keyword(92, "conscious"),
            Keyword(93, "conflict management"),
            Keyword(94, "priority"),
            Keyword(95, "order"),
            Keyword(96, "prioritization"),
            Keyword(97, "rank"),
            Keyword(98, "importance"),
            Keyword(99, "preference"),
            Keyword(100, "meeting"),
            Keyword(101, "early"),
            Keyword(102, "event"),
            Keyword(103, "feedback"),
            Keyword(104, "report"),
            Keyword(105, "newcomers"),
            Keyword(106, "support"),
            Keyword(107, "wake-up"),
            Keyword(108, "call"),
            Keyword(109, "comfortable"),
            Keyword(110, "reason"),
            Keyword(111, "attention"),
            Keyword(112, "negative consequences"),
            Keyword(114, "point out"),
            Keyword(115, "Go-to person"),
            Keyword(122, "envolvement"),
            Keyword(123, "collaboration"),
            Keyword(124, "alone"),
            Keyword(125, "step by step"),
            Keyword(126, "small step"),
            Keyword(127, "incremental"),
            Keyword(128, "short-term goal"),
            Keyword(130, "plan"),
            Keyword(131, "time-schedule"),
            Keyword(134, "high-profile person"),
            Keyword(135, "attract"),
            Keyword(138, "visibility"),
            Keyword(139, "showcase"),
            Keyword(140, "organization"),
            Keyword(141, "bridge"),
            Keyword(142, "trust"),
            Keyword(143, "early adopter"),
            Keyword(144, "connector"),
            Keyword(145, "guru"),
            Keyword(146, "adopter"),
            Keyword(147, "link"),
            Keyword(148, "joint"),
            Keyword(149, "together"),
            Keyword(150, "eat together"),
            Keyword(151, "lunch"),
            Keyword(152, "breakfast"),
            Keyword(153, "informal meeting"),
            Keyword(154, "optional meeting"),
            Keyword(155, "relaxed"),
            Keyword(156, "convenient"),
            Keyword(157, "dinner"),
            Keyword(158, "leader"),
            Keyword(159, "opinion"),
            Keyword(160, "comments"),
            Keyword(161, "resistor"),
            Keyword(162, "skeptic"),
            Keyword(163, "realist"),
            Keyword(164, "pessimist"),
            Keyword(167, "doubter"),
            Keyword(168, "resist reduction"),
            Keyword(172, "connection"),
            Keyword(173, "communicate"),
            Keyword(174, "communicator"),
            Keyword(175, "spreading"),
            Keyword(178, "corporation"),
            Keyword(181, "high-level"),
            Keyword(182, "management"),
            Keyword(183, "manager"),
            Keyword(184, "high impact"),
            Keyword(185, "lasting"),
            Keyword(188, "influencer"),
            Keyword(189, "decision maker"),
            Keyword(190, "vote"),
            Keyword(191, "approval"),
            Keyword(192, "informal"),
            Keyword(193, "decision"),
            Keyword(194, "consequences"),
            Keyword(196, "job description"),
            Keyword(197, "effectiveness"),
            Keyword(198, "effective"),
            Keyword(199, "dedication"),
            Keyword(200, "food"),
            Keyword(204, "snack"),
            Keyword(206, "impersonal"),
            Keyword(207, "food available"),
            Keyword(210, "supporter"),
            Keyword(211, "impact"),
            Keyword(212, "opinion leader"),
            Keyword(216, "increase support"),
            Keyword(217, "innovation"),
            Keyword(219, "early-adopter"),
            Keyword(220, "innovator"),
            Keyword(221, "majority"),
            Keyword(222, "establish"),
            Keyword(227, "start"),
            Keyword(228, "beginning"),
            Keyword(229, "driver"),
            Keyword(230, "evangelist"),
            Keyword(232, "external"),
            Keyword(233, "outside"),
            Keyword(234, "validation"),
            Keyword(235, "validity"),
            Keyword(237, "information"),
            Keyword(238, "independent"),
            Keyword(239, "dread"),
            Keyword(240, "afraid"),
            Keyword(242, "skeptics"),
            Keyword(243, "apprehension"),
            Keyword(244, "worry"),
            Keyword(245, "unaware"),
            Keyword(246, "group"),
            Keyword(247, "team"),
            Keyword(248, "marketing"),
            Keyword(249, "commercial"),
            Keyword(250, "identity"),
            Keyword(251, "uninformed"),
            Keyword(253, "anonym"),
            Keyword(254, "communication"),
            Keyword(257, "senior-level"),
            Keyword(258, "respect"),
            Keyword(262, "acceptance"),
            Keyword(264, "review"),
            Keyword(265, "assurance"),
            Keyword(268, "advisor"),
            Keyword(269, "evaluate"),
            Keyword(271, "stories"),
            Keyword(272, "experience"),
            Keyword(274, "interactive"),
            Keyword(276, "usefulness"),
            Keyword(278, "share"),
            Keyword(279, "knowledge"),
            Keyword(281, "jumpstart"),
            Keyword(282, "adopt"),
            Keyword(285, "sparking"),
            Keyword(289, "Involve"),
            Keyword(290, "Everyone"),
            Keyword(291, "contribution"),
            Keyword(293, "administrative"),
            Keyword(294, "technical support"),
            Keyword(296, "training"),
            Keyword(297, "change effort"),
            Keyword(300, "Just"),
            Keyword(301, "Do-It"),
            Keyword(302, "Go"),
            Keyword(303, "first-step"),
            Keyword(304, "learning"),
            Keyword(306, "work"),
            Keyword(308, "Enough"),
            Keyword(309, "difficult"),
            Keyword(310, "overselling"),
            Keyword(311, "overwhelming"),
            Keyword(312, "appropriate"),
            Keyword(313, "complex"),
            Keyword(314, "concept"),
            Keyword(315, "fundamentals"),
            Keyword(316, "description"),
            Keyword(317, "brief"),
            Keyword(318, "Local"),
            Keyword(319, "Sponsor"),
            Keyword(321, "first-line"),
            Keyword(322, "boss"),
            Keyword(324, "Location"),
            Keyword(326, "offsite"),
            Keyword(327, "nearby"),
            Keyword(329, "concentration"),
            Keyword(330, "concentrated"),
            Keyword(331, "surroundings"),
            Keyword(332, "brain storm"),
            Keyword(333, "Uncertainty"),
            Keyword(334, "Uncertain"),
            Keyword(335, "In Your Space"),
            Keyword(336, "Consistent promotion"),
            Keyword(337, "Continuous marketing"),
            Keyword(338, "Reminder"),
            Keyword(339, "personalized idea"),
            Keyword(340, "Personally useful"),
            Keyword(341, "Personally valuable"),
            Keyword(342, "build on idea"),
            Keyword(343, "Existing practices"),
            Keyword(344, "Current language"),
            Keyword(345, "Well accepted practice"),
            Keyword(346, "spark interest"),
            Keyword(347, "New idea"),
            Keyword(348, "curious"),
            Keyword(349, "carry materials"),
            Keyword(350, "buzzword"),
            Keyword(351, "persuade"),
            Keyword(352, "positive influence"),
            Keyword(353, "johnny appleseed"),
            Keyword(354, "timing"),
            Keyword(355, "Schedule events"),
            Keyword(356, "Deadlines"),
            Keyword(357, "consider timing"),
            Keyword(358, "time preferences"),
            Keyword(359, "perfect time"),
            Keyword(360, "special guest"),
            Keyword(361, "Famous person"),
            Keyword(362, "Special visitor"),
            Keyword(363, "invitation"),
            Keyword(365, "big jolt"),
            Keyword(366, "spare time"),
            Keyword(367, "supportive listeners"),
            Keyword(368, "Discouragement"),
            Keyword(369, "Encouragement"),
            Keyword(370, "embarrassing mistakes"),
            Keyword(371, "Just Say Thanks"),
            Keyword(372, "Express gratitude"),
            Keyword(373, "Thank you"),
            Keyword(374, "acknowledgement"),
            Keyword(375, "recognition"),
            Keyword(376, "celebrate small"),
            Keyword(377, "Slow progress"),
            Keyword(378, "Discourage"),
            Keyword(379, "teaching moment"),
            Keyword(382, "visible success"),
            Keyword(383, "key supporters"),
            Keyword(384, "Keep in touch"),
            Keyword(385, "Be in contact"),
            Keyword(386, "discussion group"),
            Keyword(387, "Small group"),
            Keyword(388, "Research committee"),
            Keyword(389, "proactive"),
            Keyword(390, "change initiative"),
            Keyword(391, "sustaining interest"),
            Keyword(392, "steady momentum"),
            Keyword(393, "cost and benefits"),
            Keyword(394, "Custom-fit"),
            Keyword(395, "Custom built"),
            Keyword(396, "tailor your message"),
            Keyword(397, "self-reflection"),
            Keyword(398, "Timely evaluation"),
            Keyword(399, "New approach"),
            Keyword(400, "learn from past"),
            Keyword(401, "valuable intangibles"),
            Keyword(403, "Memorandum"),
            Keyword(404, "short experiment"),
            Keyword(405, "Dummy test"),
            Keyword(406, "Tryout"),
            Keyword(407, "meet privately"),
            Keyword(408, "One-on-one meetings"),
            Keyword(409, "whisper"),
            Keyword(410, "consultant"),
            Keyword(411, "trainer"),
            Keyword(412, "confuse")
    )
}